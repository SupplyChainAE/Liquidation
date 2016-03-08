<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar"> <!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu">

<!-- 		<li id ="asn" class=" treeview"><a href="#"> -->
<!-- 			<i class="fa fa-flag"></i> <span>Advance Notification</span> <i -->
<!-- 			class="fa fa-angle-left pull-right"></i> </a> -->
<!-- 		<ul class="treeview-menu"> -->
<!-- 			<li id="asnupload"> -->
<%-- 			<a href="<c:url value="/ASN/home"/>"><i	class="fa fa-circle-o"></i>Upload</a> --%>
<!-- 			</li> -->
<!-- 			<li id="asnsearch"><a href="#"> -->
<!-- 			<i class="fa fa-circle-o"></i> <span>Search</span> <i -->
<!-- 			class="fa fa-angle-left pull-right"></i> </a> -->
<!-- 			<ul class="treeview-menu"> -->
<%-- 			<li><a href="<c:url value="/ASN/search"/>"><i	class="fa fa-circle-o"></i>Search By Date</a> --%>
<!-- 			</li> -->
<!-- 			<li id="asnview"> -->
<%-- 			<a href="<c:url value="/ASN/searchByCode"/>"> --%>
<!-- 			<i class="fa fa-circle-o"></i>Search By Code</a> -->
<!-- 			</li>	 -->
<!-- 		</ul>		 -->
<!-- 			</li>	 -->
<!-- 		</ul></li> -->

		<security:authorize
				access="hasAnyRole('ROLE_ADMIN','ROLE_NORMAL','ROLE_SUPERVISOR')">
		<li id ="stockin" class=" treeview"><a href="<c:url value="/StockIn/home"/>">
			<i class="fa fa-eject"></i> <span>Stock In</span> </a>
<!-- 		<ul class="treeview-menu"> -->
<!-- 			<li id="stockinhome"> -->
<%-- 			<a href="<c:url value="/StockIn/home"/>"><i	class="fa fa-circle-o"></i>Start Stock In</a> --%>
<!-- 			</li> -->
<!-- 			<li id="stockinupload"> -->
<%-- 			<a href="<c:url value="/StockIn/uploadHome"/>"><i	class="fa fa-circle-o"></i>Upload</a> --%>
<!-- 			</li> -->
<!-- 		</ul> -->
			</li></security:authorize>
		<security:authorize
				access="hasAnyRole('ROLE_ADMIN','ROLE_NORMAL','ROLE_SUPERVISOR')">
		<li id ="dispatch" class=" treeview"><a href="#">
			<i class="fa fa-truck"></i> <span>Dispatch</span> <i
			class="fa fa-angle-left pull-right"></i> </a>
		<ul class="treeview-menu">
			<li id="dispatchlot">
			<a href="<c:url value="/Dispatch/home"/>"><i	class="fa fa-circle-o"></i>Lot Dispatch</a>
			</li>
<!-- 			<li id="dispatchcri"> -->
<%-- 			<a href="<c:url value="/Dispatch/criHome"/>"><i	class="fa fa-circle-o"></i>CRI Dispatch</a> --%>
<!-- 			</li> -->
		</ul>
			</li>
		</security:authorize>
		<security:authorize
				access="hasAnyRole('ROLE_ADMIN','ROLE_NORMAL','ROLE_SUPERVISOR')">
		<li id ="view" class=" treeview"><a href="#">
			<i class="fa fa-list-alt"></i> <span>View</span> <i
			class="fa fa-angle-left pull-right"></i> </a>
		<ul class="treeview-menu">
			<li >
			<a href="<c:url value="/View/home"/>"><i class="fa fa-circle-o"></i>Lot Details</a>
			</li>
			<li >
			<a href="<c:url value="/View/searchCri"/>"><i	class="fa fa-circle-o"></i>Search</a>
			</li>
		</ul>
			</li>
			</security:authorize>
		<security:authorize
				access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR')">
		<li id ="lot" class=" treeview"><a href="<c:url value="/LotCreate/home"/>">
			<i class="fa fa-cubes"></i> <span>Create Lot</span></a>
		</li>
		</security:authorize>
		
		<security:authorize	access="hasAnyRole('ROLE_ADMIN')">
		<li id ="user" class=" treeview"><a href="<c:url value="/User"/>">
			<i class="fa fa-user"></i> <span>User</span> <i
			class="fa fa-angle-left pull-right"></i> </a>
		<ul class="treeview-menu">
			<li id="usercreate">
			<a href="<c:url value="/User/create"/>"><i	class="fa fa-circle-o"></i>Create</a>
			</li>
			<li id="useredit">
			<a href="<c:url value="/User/view"/>">
			<i class="fa fa-circle-o"></i>View/Edit</a>
			</li>	
		</ul></li>
		</security:authorize>
		
		<security:authorize access="hasAnyRole('ROLE_ADMIN')">
		<li id ="liq" class=" treeview"><a href="<c:url value="#"/>">
			<i class="fa fa-archive"></i> <span>Liquidation Center</span> <i
			class="fa fa-angle-left pull-right"></i> </a>
		<ul class="treeview-menu">
			<li id="usercreate">
			<a href="<c:url value="/Liquidation/create"/>"><i	class="fa fa-circle-o"></i>Create</a>
			</li>
			<li id="useredit">
			<a href="<c:url value="/Liquidation/view"/>">
			<i class="fa fa-circle-o"></i>View/Edit</a>
			</li>	
		</ul></li>
		</security:authorize>		
		

</ul>
</section> <!-- /.sidebar --> </aside>
