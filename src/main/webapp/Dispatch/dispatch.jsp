<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Dispatch">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#dispatch').addClass("active");
		$("#inventoryTable").DataTable({
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
           Dispatch
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
                 <div class="box-body">
                 <table class="table table-bordered table-hover" id="inventoryTable">
           			<thead>
           				<tr>
           				<th>Code</th>
           				<th>SUPC</th>
           				<th>Lot Name</th>
           				<th>Product Name</th>
           				<th>Price</th>
           				<th>Category</th>
           				</tr>
           			</thead>
           		
           			<tbody>
           			<c:forEach var="inventory" items="${dispatchList}">
           			<tr>
           				<td>${inventory.barcode}</td>
           				<td>${inventory.supc}</td>
           				<td>${inventory.lot.lotName}</td>
           				<td>${inventory.productName}</td>
           				<td>${inventory.price}</td>
           				<td>${inventory.liqCategory}</td>
           			</tr>
           			</c:forEach>
           			</tbody>
           			</table>
			</div>
			<br><br><br>
			<div class="box-footer"></div>
			   
                 <div class="box-body">
                 <form name="buyerForm" method="post" action="<c:url value="/Dispatch/dispatchLot"/>">
           		<c:forEach var="inv" items="${dispatchList}">		
                 <input type="hidden" value="${inv.id}" name="invId"/>
                 </c:forEach>
                  	
           			<div class="form-group col-xs-6">
                      <label for="name">Buyer Name</label>
                      <input class="form-control required" name="name" id="name" placeholder="Enter Buyer Name" required ="true">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="addressLine1">Address Line 1</label>
                      <input class="form-control required" name="addressLine1" id="addressLine1" placeholder="Enter Address" required ="true">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="addressLine2">Address Line 2</label>
                      <input class="form-control" name="addressLine2" id="addressLine2" placeholder="Enter Address">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="state">State</label>
                      <input class="form-control required" name="state" id="state" placeholder="Enter State" required ="true">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="city">City</label>
                      <input class="form-control required" name="city" id="city" placeholder="Enter City" required ="true">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="pincode">Pincode</label>
                      <input class="form-control required" name="pincode" id="pincode" placeholder="Enter Pincode" required ="true">
                    </div>
                    
                     <div class="form-group col-xs-6">
                      <label for="contact">Contact</label>
                      <input class="form-control required" name="contact" id="contact" placeholder="Enter Contact" required ="true">
                    </div>
                    
                     <div class="form-group col-xs-6">
                      <label for="email">Email</label>
                      <input class="form-control required" name="email" id="email" placeholder="Enter Email">
                    </div>
                    
                     <div class="form-group col-xs-6">
                      <label for="price">Price</label>
                      <input class="form-control required" name="price" id="price" placeholder="Enter Price" required ="true">
                    </div>
                   
                      <div class="form-group col-xs-6">
                      <label for="comments">Comments</label>
                      <input class="form-control" name="comments" id="comments" placeholder="Enter Comments">
                    </div>
                   
                    <div class="form-group" align="center">
                      
                      <input class="btn btn-danger	" type="submit" value="Dispatch">
                    </div>
           			</form>    	 
           			</div>			
				</div>
			</div>
			</div>
			
		
</jsp:body>
</tags:page>