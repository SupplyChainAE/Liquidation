<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="StockIn">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#putaway').addClass("active");
		
// 		 $('#barcode').keyup(function(e) {
			
		      
// 		        if ($(this).val().length > 0) {
// 		        	 alert("here");
// 		           $('#main-form').submit();
// 		        }
// 		     });
			});
</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
           Stock IN
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
                  	<div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}
                    </div>
                  </c:if>
                 <div class="row">	
                 <form method="post" id="main-form" action="<c:url value="/StockIn/check"/>">
        		  
                  <div class="col-xs-6">
                  	<div class="box-body">
                  	 
                  	<div class="form-group">
                  	 	<div class="row">
                     	<div class="col-md-6">	
                     		<h4 >Current Lot : ${lot.lotName}</h4></div>
                     	<div class="col-md-6">
                     		<h4>Current Lot Size : ${lot.currentSize }</h4>
                    	</div>
                    	</div>
                    </div>
                  	 
                  	 <div class="form-group">
                     <label for="barcode">Enter Code</label>
                      <input class="form-control required" name="barcode" id="barcode" required ="true"/>
                    </div>
                    <br>
                      <div class="form-group">
                      <input type="hidden" value="${lot}" name="lot"/>
                      <input class="btn btn-primary	" type="submit" value="Get Details">
                    </div>
				</div>
				</div>
			</form>
			</div>
			</div>
			<div class="box-footer"></div>
			
<!-- 			</div> -->
		
			<c:if test="${not empty product}">
			<div class="box">
                 <div class="box-body">
           			<table name="inv" class="table table-bordered" id="inv">
           			<thead>
           				<tr>
           				<th>CRI Ticket</th>
           				<th>SubOrder</th>
           				<th>Item</th>
           				<th>SubCategory</th>
           				<th>Liquidation Category</th>
           				<th>Price</th>
<!--            				<th>Remarks</th> -->
           				</tr>
           			</thead>
           			<tbody>
           			<tr>
           				<td>${product.ticketId}</td>
           				<td>${product.suborderCode}</td>
           				<td>${product.productName}</td>
           				<td>${product.subCategory}</td>
           				<td>${product.liqCategory}</td>
           				<td>${product.price}</td>
<!--            				<td><textarea name="remarks"></textarea></td> -->
           			</tr>
           			</tbody>
           			</table>  
           			<br>
           			<form method="post" action="<c:url value="/StockIn/save"/>">
<%--            			 <input type="hidden" name="product" value="${product}"> --%>
           			 <div align="center" class="form-groups">
           			 <label>Remarks</label>
           			 </div>
           			 <div align="center" class="form-group">
                     <textarea rows="4" cols="100%" name="remarks"></textarea>
                    </div><br>
           			 <div align="center" class="form-group">
                      <input class="btn btn-success	" type="submit" value="Stock In">
                    </div>
                    </form>  	 			
				</div>
			</div>
		  </c:if>
		  </div>
		  </div>
		</div>
</jsp:body>
</tags:page>