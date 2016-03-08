<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="http://www.springframework.org/security/tags"	prefix="security"%>
<%@ page import="com.snapdeal.entity.User"%>
<%
	User sessionUser = (User) SecurityContextHolder.getContext() 
 			.getAuthentication().getPrincipal();
	
	
	pageContext.setAttribute("activeLcenter", sessionUser.getActiveLiquidation().getName());
	pageContext.setAttribute("userLcenter",sessionUser.getLiquidationList());

 	pageContext.setAttribute("name", sessionUser.getUserName()); 
 	pageContext.setAttribute("id", sessionUser.getId()); 
%>  

     <a href="<c:url value="/home"/>" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini">LMS</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>WMS - Liquidation </b></span>
        </a>		
<nav class="navbar navbar-static-top" role="navigation">
     		
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          
<!--                		<div class="container"> -->
	
<!-- 	<div class="nav-no-collapse header-nav" style="margin-left: 20%"> -->
<!-- 		 <div class="navbar-custom-menu"> -->
<!-- 		<ul class="nav navbar-nav"> -->
		
<!-- 		</ul> -->
<!-- 	</div> -->

     		
<!-- 					<div class="name"> -->
<%-- 						<span class="hello">${activeLCenter}</span> --%>
<!-- 					</div> -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
            <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <span class="hidden-xs">${activeLcenter}</span>
                </a>
            	
				<ul class="dropdown-menu" style="margin-left: 20%; width: 100%">
					
					<c:forEach items="${userLcenter}" var="userLcenter">
						<c:if test="${userLcenter.enabled}">
							<li><a
								href='<c:url value="/Liquidation/change/${userLcenter.id}"/>'>${userLcenter.name}</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</li>		          
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<c:url value="/img/sd.png"/>" class="user-image" alt="User Image" />
                  <span class="hidden-xs">${name}</span>
                </a>
                <ul class="dropdown-menu" style="margin-left: 20%; width: 100%">
                    <li>
                   	<a href="<c:url value="/User/changePassword/${id}"/>">Change Password</a>
                    
                    </li>
                    <li >
                      <a href="<c:url value="/j_spring_security_logout"/>" class="btn btn-default btn-flat">Sign out</a>
                    
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>