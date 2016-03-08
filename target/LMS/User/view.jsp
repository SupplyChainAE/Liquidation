<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
<jsp:attribute name="script">
<script>
$(document).ready(function(){
$('#useredit').addClass("active");
$("#userTable").DataTable({
	"paging": true,
    "lengthChange": false,
    "searching": false,
    "ordering": true,
    "info": true,
    "autoWidth": false
});
});
</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
           Users
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
            <div class="box-body">
              
            </div><!-- /.box-body -->
        <div class="box-footer"></div>
					<!-- /.box-header -->
                <div class="box-body">
                 <c:if test="${not empty message}">	  	
                  	<div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}
                    </div>
                  </c:if>
                    <table id="userTable" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>UserName</th>
                        <th>Role</th>
                        <th>Liquidation Centres</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${users}">
                    <tr>
                        <td>${item.userName}</td>
                        <td><c:forEach var="roles" items="${item.userRoles}">
                        	${roles.role}<br>
                        	</c:forEach></td>
                        <td>
                        <c:forEach var="liq" items="${item.liquidationList}">
                        	${liq.name}<br>
                        </c:forEach>
                        </td>
                        <td>
                        <c:choose>
							<c:when test="${item.enabled}">
								<a
										href='<c:url value="/User/disable/${item.id}"></c:url>'
										class="btn btn-danger" style="margin-left: 20px">Disable</a>
							</c:when>
							<c:otherwise>
								<a
										href='<c:url value="/User/enable/${item.id}"></c:url>'
										class="btn btn-success" style="margin-left: 20px">Enable</a>
							</c:otherwise>
						</c:choose>&nbsp;&nbsp;
                        <a href=<c:url value="/User/edit/${item.id}"/> class="btn btn-default">Edit</a>&nbsp;&nbsp;
                        <a href=<c:url value="/User/changePassword/${item.id}"/>>changepassword</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                    </table>
				</div>
		</div>
		</div>
		</div>
</jsp:body>
</tags:page>