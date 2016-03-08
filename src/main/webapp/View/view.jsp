<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Liquidation Center">
<jsp:attribute name="script">
<script>
$(document).ready(function(){
$('#view').addClass("active");
$("#lotTable").DataTable({
	"paging": true,
    "lengthChange": false,
    "searching": true,
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
           Lot Details
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
                    <table id="lotTable" class="table table-bordered table-striped table-hover">
                    <thead>
                      <tr>
                        <th>Lot Name</th>
                        <th>Lot Number</th>
                        <th>Current Size</th>
                        <th>Status</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="lot" items="${lots}">
                    <tr>
                        <td>${lot.lotName}</td>
                        <td>${lot.lotNumber}</td>
                        <td>${lot.currentSize}</td>
                        <td>${lot.status}</td>
                        <td><a href="<c:url value="/View/viewLot/${lot.id}"/>" class="btn btn-default">Details</a>&nbsp;&nbsp;<a href="<c:url value="/View/downloadLot/${lot.id}"/>" class="btn btn-primary">Download</a></td>	
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