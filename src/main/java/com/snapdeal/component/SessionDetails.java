package com.snapdeal.component;

import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.snapdeal.entity.Liquidation;
import com.snapdeal.entity.User;

@Component
@Named("sessionDetails")
public class SessionDetails {

	public User getSessionUser()
	{
		User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return sessionUser;
	}
	
	public Long getActiveLcenter()
	{
		User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return sessionUser.getActiveLiquidation().getId();
	}
	
	public Liquidation getSessionLcenter()
	{
		User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return sessionUser.getActiveLiquidation();
	}
	
}
