package com.snapdeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.Roles;
import com.snapdeal.entity.User;
import com.snapdeal.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {

	@Inject
	@Named("userService")
	UserService userService;

	@RequestMapping("/create")
	public String createUser(ModelMap map) {
		List<Liquidation> lCenterList = userService.getAllLiquidations();
		List<Roles> roleList = userService.getAllRoles();
		map.put("lcenter", lCenterList);
		map.put("roles", roleList);
		map.put("user", new User());
		return "User/create";
	}
	
	@RequestMapping("/checkUser")
	public @ResponseBody String checkUser(@ModelAttribute("name") String userName)
	{
		boolean result = userService.checkUser(userName);
		if(result)
		{
			return "success";
		}
		else{
			return "failure";
		}
	}
	
	@RequestMapping(value="/disable/{id}",method=RequestMethod.GET)
	public String disableUser(@PathVariable("id") Long id,ModelMap map)
	{
		userService.disableUser(id);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> userList = userService.getUsersExceptLoggedIn(userName);
		map.put("users", userList);
		map.put("message", "User disabled Successfully");
		return "User/view";
	}

	@RequestMapping(value="/enable/{id}",method=RequestMethod.GET)
	public String enableUser(@PathVariable("id") Long id,ModelMap map)
	{
		userService.enableUser(id);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> userList = userService.getUsersExceptLoggedIn(userName);
		map.put("users", userList);
		map.put("message", "User enabled Successfully");
		return "User/view";
	}

	
	
	
	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("user") User user, @RequestParam("role") Long[] roles,
				@RequestParam("lCenter") Long[] lcenterList,ModelMap map) {
		System.out.println(user.getUsername());
		List<Roles> finalRoles = new ArrayList<Roles>();
		List<Liquidation> finalLiquidations = new ArrayList<Liquidation>();
		
		for(Long roleId : roles)
		{
			Roles r = new Roles();
			r.setId(roleId);
			finalRoles.add(r);
		}
		for(Long lCenter : lcenterList)
		{
			Liquidation lq = new Liquidation();
			lq.setId(lCenter);
			finalLiquidations.add(lq);
		} 
		if(user.getId() == null)
		{
			user.setUserRoles(finalRoles);
			user.setLiquidationList(finalLiquidations);
			user.setActiveLiquidation(finalLiquidations.get(0));
			userService.saveOrUpdateUser(user);
		}
		else
		{
			 User persistedUser = userService.findUserById(user.getId());
			 persistedUser.setUserName(user.getUsername());
			 persistedUser.setUserRoles(finalRoles);
			 persistedUser.setLiquidationList(finalLiquidations);
			 userService.saveOrUpdateUser(persistedUser);
		 }
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> userList = userService.getUsersExceptLoggedIn(currentUser);
		map.put("users", userList);
		map.put("message", "User saved Successfully");
		return "User/view";
	}

	@RequestMapping("/view")
	public String showUsers(ModelMap map) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> userList = userService.getUsersExceptLoggedIn(currentUser);
		map.put("users", userList);
		return "User/view";
	}

	@RequestMapping("/edit/{id}")
	public String editUser(@PathVariable("id") Long id, ModelMap map) {
		User persistedUser = userService.findUserById(id);
		List<Liquidation> lCenterList = userService.getAllLiquidations();
		List<Roles> roleList = userService.getAllRoles();
		map.put("lCeneter", lCenterList);
		map.put("roles", roleList);
		map.put("user", persistedUser);
		
		map.put("message", "User saved Successfully");
		return "User/edit";
	}

	@RequestMapping("/changePassword/{id}")
	public String changePassword(@PathVariable("id") Long id, ModelMap map) {
		User user = userService.findUserById(id);

		map.put("userName", user.getUsername());
		map.put("userid", id);
		return "User/changePassword";
	}

	
	@RequestMapping("/updatePassword")
	public String updatePassword(@ModelAttribute("id") Long id,
			@ModelAttribute("password") String password, ModelMap map) {
		User persistedUser = userService.findUserById(id);
		persistedUser.setPassword(getHashedPassword(password));
		userService.saveOrUpdateUser(persistedUser);
		map.put("message","Password Changed for "+persistedUser.getUsername());
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> userList = userService.getUsersExceptLoggedIn(currentUser);
		map.put("users", userList);
		
		return "User/view";
	}
	
	@RequestMapping("/updatePasswordUser")
	public String updatePasswordUser(@ModelAttribute("id") Long id,
			@ModelAttribute("password") String password, ModelMap map) {
		User persistedUser = userService.findUserById(id);
		persistedUser.setPassword(getHashedPassword(password));
		userService.saveOrUpdateUser(persistedUser);
		map.put("message","Password Changed");
		return "home";
	}

	public String getHashedPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
}
