<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Dispatch">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#dispatch').addClass("active");
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
                 <table class="table table-striped" >
           			<thead>
           				<tr>
           				<th>Code</th>
           				<th>SUPC</th>
           				<th>Lot Number</th>
           				<th>Product Name</th>
           				<th>Price</th>
           				<th>Seller Code</th>
           				</tr>
           			</thead>
           			<tbody>
           			<tr>
           				<td>${inventory.barcode}</td>
           				<td>${inventory.supc}</td>
           				<td>${inventory.lot.lotNumber}</td>
           				<td>${inventory.productName}</td>
           				<td>${inventory.price}</td>
           				<td>${inventory.vendorCode}</td>
           			</tr>
           			</tbody>
           			</table>
			</div>
			
			<div class="box-footer"></div>
			
			
			<div class="col-xs-14">    
			     
                 <div class="box-body">
                 <form name="buyerForm" method="post" action="<c:url value="/Dispatch/dispatch"/>">
           				
                 <input type="hidden" value="${inventory.id}" name="invId"/>
                  	
           			<div class="form-group col-xs-6">
                      <label for="name">Buyer Name</label>
                      <input class="form-control" name="name" id="name" placeholder="Enter Buyer Name">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="addressLine1">Address Line 1</label>
                      <input class="form-control" name="addressLine1" id="addressLine1" placeholder="Enter Address">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="addressLine2">Address Line 2</label>
                      <input class="form-control" name="addressLine2" id="addressLine2" placeholder="Enter Address">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="state">State</label>
                      <input class="form-control" name="state" id="state" placeholder="Enter State">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="city">City</label>
                      <input class="form-control" name="city" id="city" placeholder="Enter City">
                    </div>
                    
                    <div class="form-group col-xs-6">
                      <label for="pincode">Pincode</label>
                      <input class="form-control" name="pincode" id="pincode" placeholder="Enter Pincode">
                    </div>
                    
                     <div class="form-group col-xs-6">
                      <label for="contact">Contact</label>
                      <input class="form-control" name="contact" id="contact" placeholder="Enter Contact">
                    </div>
                    
                     <div class="form-group col-xs-6">
                      <label for="pincode">Email</label>
                      <input class="form-control" name="email" id="email" placeholder="Enter Email">
                    </div>
                    
                     <div class="form-group col-xs-6">
                      <label for="pincode">Price</label>
                      <input class="form-control" name="price" id="price" placeholder="Enter Price">
                    </div>
                   
                      <div class="form-group col-xs-6">
                      <label for="pincode">Comments</label>
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
			
		  </div>
		  
		
</jsp:body>
</tags:page>