<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="ASN">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#asnsearch').addClass("active");
			});
</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
           Advance Shipping Notification
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
                 <div class="box-body">
                 <c:if test="${not empty message}">	  	
                  	<div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}
                    </div>
                  </c:if>
                 <div class="row">	
                 <form method="post"  action="<c:url value="/ASN/upload"/>">
        		  
                  <div class="col-xs-6">
                  	<div class="box-body">
                  	
                  	 <div class="form-group">
                     <label for="barcode">Enter Code</label>
                      <input class="form-control required" name="barcode" id="barcode"/>
                    </div>
                      <div class="form-group">
                      <input class="btn btn-primary" type="submit">
                    </div>
				</div>
				</div>
			</form>
			</div>
			</div>
			</div>
			<div class="box-footer"></div>
		
			<c:if test="${not empty asn}">
			<div class="box">
                 <div class="box-body">
           			<table name="asnData" id="asnData">
           			
           			<thead>
           				<tr>
           				<th>Code</th>
           				<th>Expected Date</th>
           				<th>Received </th>
           				<th>Receive Date</th>
           				</tr>
           			</thead>
           			<tbody>
           			<tr>
           				<td>${asn.barcode}</td>
           				<td>${asn.expectedDate}</td>
           				<td>${asn.isUsed}</td>
           				<td><c:if test="${not empty asn.receivedDate}">${asn.receiveDate}</c:if></td>
           			</tr>
           			</tbody>
           			</table>    	 			
				</div>
			</div>
		  </c:if>
		  </div>
		  
		</div>
</jsp:body>
</tags:page>