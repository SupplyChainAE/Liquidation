<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
<jsp:attribute name="script">
<script>
$(document).ready(function(){
$('#view').addClass('active');
});
</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
           Search
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
                 <div class="box-body">
                 <c:if test="${not empty message and error == 'true'}">	  	
                  	<div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}
                    </div>
                  </c:if>
                 <div class="row">	
                 <form method="post"  action="<c:url value="/View/search"/>">
        		  
                  <div class="col-xs-6">
                  	<div class="box-body">
                  	
                  	 <div class="form-group">
                     <label for="barcode">Enter Code</label>
                      <input class="form-control required" name="barcode" id="barcode" required ="true"/>
                    </div>
                      <div class="form-group">
                      <input class="btn btn-primary	" type="submit" value="Search">
                    </div>
				</div>
				</div>
			</form>
			</div>
			</div>
			<div class="box-footer"></div>
			
			</div>
			
			<c:if test="${not empty inventory}">
			<div class="box">
                 <div class="box-body">
           			<table name="inv" class="table table-bordered" id="inv">
           			<thead>
           				<tr>
           				<th>Details</th>
           				<th></th>
           				</tr>
           			</thead>
           			<tbody>
           			<tr>
					<td><b>BarCode</b></td>
					<td>${inventory.barcode}</td>
					</tr>
					
					<tr>
					<td><b>Customer Name</b></td>
					<td>${inventory.customerName}</td>
					</tr>
															
					<tr>
					<td><b>issue Category</b></td>
					<td>${inventory.issueCategory}</td>
					</tr>
					
					<tr>
					<td><b>Order Code</b></td>
					<td>${inventory.orderCode}</td>
					</tr>
					
					<tr>
					<td><b>Price</b></td>
					<td>${inventory.price}</td>
					</tr>
					
					<tr>
					<td><b>Product Name</b></td>
					<td>${inventory.productName}</td>
					</tr>
					<tr>
					<td><b>Fulfilment Model</td>
					<td>${inventory.fulfillmentModel}</td>
					</tr>
					<tr>
					<td><b>RMS Center</b></td>
					<td>${inventory.rmsCenter}</td>
					</tr>
					
					<tr>
					<td><b>Seller Name</b></td>
					<td>${inventory.sellerName}</td>
					</tr>
					
					<tr>
					<td><b>Seller Code</b></td>
					<td>${inventory.vendorCode}</td>
					</tr>
					
					<tr>
					<td><b>SKU</b></td>
					<td>${inventory.sku}</td>
					</tr>
					
					<tr>
					<td><b>SUPC</b></td>
					<td>${inventory.supc}</td>
					</tr>
					
					<tr>
					<td><b>Status</b></td>
					<td>${inventory.status}</td>
					</tr>
					
					<tr>
					<td><b>Sub Category</b></td>
					<td>${inventory.subCategory}</td>
					</tr>
					
					<tr>
					<td><b>Liquidation Category</b></td>
					<td>${inventory.liqCategory}</td>
					</tr>
					
					<tr>
					<td><b>SubOrder Code</b></td>
					<td>${inventory.suborderCode}</td>
					</tr>
					
					<tr>
					<td><b>Ticket ID</b></td>
					<td>${inventory.ticketId}</td>
					</tr>
					
					<tr>
					<td><b>Weight</b></td>
					<td>${inventory.weight}</td>
					</tr>
					
 					<tr>
 					<td><b>Lot Number</b></td>
 					<td>${inventory.lot.lotNumber}</td>
 					</tr>
					
					<tr>
					<td><b>Created</b></td>
					<td>${inventory.created}</td>
					</tr>
					
					<tr>
					<td><b>Updated</b></td>
					<td>${inventory.updated}</td>
					</tr>
				
 					<c:if test="${not empty  inventory.buyer }">
 					<tr>
 					<td><b>Buyer Name</b></td>
 					<td>${inventory.buyer.name}</td>
 					</tr>
           			
           			<tr>
 					<td><b>Buyer Address Line1</b></td>
 					<td>${inventory.buyer.addressLine1}</td>
 					</tr>
           			
           			<tr>
 					<td><b>Buyer Address Line2</b></td>
 					<td>${inventory.buyer.addressLine2}</td>
 					</tr>
           			
           			<tr>
 					<td><b>Buyer State</b></td>
 					<td>${inventory.buyer.state}</td>
 					</tr>
           			
           			<tr>
 					<td><b>Buyer City</b></td>
 					<td>${inventory.buyer.city}</td>
 					</tr>
           			
           			<tr>
 					<td><b>Buyer Pincode</b></td>
 					<td>${inventory.buyer.pincode}</td>
 					</tr>
           			
           			<tr>
 					<td><b>Buyer Contact Number</b></td>
 					<td>${inventory.buyer.contact}</td>
 					</tr>
           			
           			<tr>
 					<td><b>Buyer Email</b></td>
 					<td>${inventory.buyer.email}</td>
 					</tr>
           			
           			<tr>
 					<td><b>Buyer Price</b></td>
 					<td>${inventory.buyer.price}</td>
 					</tr>
 					
 					<tr>
 					<td><b>Comments</b></td>
 					<td>${inventory.buyer.comments}</td>
 					</tr>
 					
           			</c:if>
           			</tbody>
           			</table>    	 			
				</div>
			</div>
		  </c:if>
		  </div>
		  
		</div>
</jsp:body>
</tags:page>