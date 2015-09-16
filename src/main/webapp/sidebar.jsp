<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar"> <!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu">

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

</ul>
</section> <!-- /.sidebar --> </aside>
