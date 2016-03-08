<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Dispatch">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#dispatch').addClass("active");
		
		var toggle =0;
		 $("#allCheck").click(function() {
		        var checkBoxes = $(".selectCheck");
		        if(toggle == 0){
		        	checkBoxes.prop("checked", true);
		        	toggle = 1;
		        }
		        else{
		        	checkBoxes.prop("checked", false);
		        	toggle = 0;
		        }
		    });
		 
		 $("#form").validate();
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
                 <c:if test="${not empty message and error == 'true'}">	  	
                  	<div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}
                    </div>
                  </c:if>
                 <c:if test="${not empty message and error == 'false'}">	  	
                  	<div class="alert alert-danger alert-success">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}
                    </div>
                  </c:if>
                 <div class="row">	
                 <form method="post" id="form" action="<c:url value="/Dispatch/check"/>">
        		  
                  <div class="col-xs-6">
                  	<div class="box-body">
                  	
                  	 <div class="form-group">
                     <label for="barcode">Enter Barcode</label>
                      <input class="form-control  required" name="barcode" id="barcode" required ="true"/>
                    </div>
                      <div class="form-group">
                      <input class="btn btn-primary	" type="submit" value="Submit">
                    </div>
				</div>
				</div>
			</form>
			</div>
			</div>
			<div class="box-footer"></div>
			
			
			<c:if test="${not empty inventory}">
			
                 <div class="box-body">
                 <form name="dispatchForm" method="post" action="<c:url value="/Dispatch/buyerDetailsCri"/>">
           			<input type="hidden" name="invId" value="${inventory.id}"/>
           			<table class="form-group table" id="inventoryTable">
           			<thead>
           				<tr>
           				<th>Code</th>
           				<th>SUPC</th>
           				<th>Lot Number</th>
           				<th>Product Name</th>
           				<th>Price</th>
           				<th>Status</th>
           				</tr>
           			</thead>
           			<tbody>
           			<tr>
           				<td>${inventory.barcode}</td>
           				<td>${inventory.supc}</td>
           				<td>${inventory.lot.lotNumber}</td>
           				<td>${inventory.productName}</td>
           				<td>${inventory.price}</td>
           				<td>${inventory.status}</td>
           				
           			</tr>
           			</tbody>
           			</table>
           			<div class="form-group">
                      <input class="btn btn-primary	" type="submit" value="Forward to Dispatch">
                    </div>
           			</form>    	 			
				</div>
			
		  </c:if>
		  </div>
		  </div>
		</div>
</jsp:body>
</tags:page>