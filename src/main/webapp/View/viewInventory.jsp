<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tags:page title="Liquidation Center">
<jsp:attribute name="script">
<script>
$(document).ready(function(){
$('#view').addClass("active");
$('#example-modal').hide();

$("#inventoryTable").DataTable({
	"paging": true,
    "lengthChange": true,
    "searching": true,
    "ordering": true,
    "info": false,
    "autoWidth": true
});
});

function getInventory(id)
{
	$('#example-modal').hide();
	$("#tablebody").empty();
	$.ajax({
		url : "/LMS/View/inventoryDetails",
		dataType : "JSON",
		contentType : 'application/json',
		type : 'GET',
		data : "id="+id,
		success : function(data){
						console.log(data);
				var createdate=new Date(parseInt(data.created));
				var updatedate=new Date(parseInt(data.updated));
					
					$("#tablebody").append("<tr><td><b>Barcode</td><td>"+data.barcode+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Customer Name</td><td>"+data.customerName+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Issue Category</td><td>"+data.issueCategory+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Order Code</td><td>"+data.orderCode+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Fulfillment Model</td><td>"+data.fulfillmentModel+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Price</td><td>"+data.price+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Product Name</td><td>"+data.productName+"</td></tr>");
					$("#tablebody").append("<tr><td><b>RMS Center</td><td>"+data.rmsCenter+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Seller Name</td><td>"+data.sellerName+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Seller Code</td><td>"+data.vendorCode+"</td></tr>");
					$("#tablebody").append("<tr><td><b>SKU</td><td>"+data.sku+"</td></tr>");
					$("#tablebody").append("<tr><td><b>SUPC</td><td>"+data.supc+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Status</td><td>"+data.status+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Sub Category</td><td>"+data.subCategory+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Liq Category</td><td>"+data.liqCategory+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Suborder Code</td><td>"+data.suborderCode+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Ticket ID</td><td>"+data.ticketId+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Weight</td><td>"+data.weight+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Lot Number</td><td>"+data.lot.lotNumber+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Created</td><td>"+createdate+"</td></tr>");
					$("#tablebody").append("<tr><td><b>Updated</td><td>"+updatedate+"</td></tr>");
		},
	error : function(xhr, textStatus, errorThrown){
		 alert('request failed '+errorThrown+"  ");
		 
	 }
	});	
	$('#example-modal').show();
}


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
<!--                 <div> -->
				<div class="box-body">
					<h3>Lot : ${lot.lotName}</h3> 
<!--                 <div class="box-body"style="float:right;"> -->
                <h4  style="float:right">Total Selling Amount of Lot  : ${lot.dispatchPrice}</h4>
                </div>
<!--                 </div> -->
                    <table id="inventoryTable" class="table table-bordered table-striped table-hover">
                    <thead>
                      <tr>
                        <th>Code</th>
                        <th>Product Name</th>
                        <th>SubCategory</th>
                        <th>Seller Code</th>
                        <th>Liquidation Category</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Create Date</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="inv1" items="${inventory}" varStatus="index">
                    <tr>
                        <td>${inv1.barcode}</td>
                        <td>${inv1.productName}</td>
                        <td>${inv1.subCategory}</td>
                        <td>${inv1.vendorCode}</td>
                        <td>${inv1.liqCategory}</td>
                        <td>${inv1.price}</td>
                        <td>${inv1.status}</td>
                        <td><fmt:formatDate value="${inv1.created}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<%--                         <td><a href="<c:url value="/View/inventoryDetails/${lot.id}/${inv1.id}"/>">Details</a></td>	 --%>
                    <td><a href="#example-modal" class="btn btn-default" onclick="getInventory(${inv1.id});">Details</a></td>          
                    </tr>
                    </c:forEach>
                    </tbody>
                    </table>
				</div>
           
  	<br><br>            
          <div  id="example-modal">
                           
                    <table id="invtable" class="table table-bordered table table-striped">
                    <thead>
                    	<tr>
                    	<th><b>Inventory Details</b></th>
                    	<th></th>
                    	</tr>
                    </thead>
                    <tbody id="tablebody">
                    </tbody>
                    </table>
<!--          </div></div> -->
         </div>
        </div></div>
</jsp:body>
</tags:page>