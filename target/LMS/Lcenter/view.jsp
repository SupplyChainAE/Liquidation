<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Liquidation Center">
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
           Liquidation Centers
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
                    <table id="userTable" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Code</th>
                        <th>Name</th>
<!--                         <th>Lot Size</th> -->
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="lcenter" items="${lcenter}">
                    <tr>
                        <td>${lcenter.code}</td>
                        <td>${lcenter.name}</td>
<%--                         <td>${lcenter.lotSize}</td>	 --%>
                        <td>
                        <c:choose>
							<c:when test="${lcenter.enabled}">
								<a
										href='<c:url value="/Liquidation/disable/${lcenter.id}"></c:url>'
										class="btn btn-danger" style="margin-left: 20px">Disable</a>
							</c:when>
							<c:otherwise>
								<a
										href='<c:url value="/Liquidation/enable/${lcenter.id}"></c:url>'
										class="btn btn-success" style="margin-left: 20px">Enable</a>
							</c:otherwise>
						</c:choose>
                        &nbsp;&nbsp;
                        <a href=<c:url value="/Liquidation/edit/${lcenter.id}"/> class="btn btn-default">Edit</a>&nbsp;&nbsp;
                        
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