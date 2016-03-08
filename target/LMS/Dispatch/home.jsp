<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Dispatch">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#dispatch').addClass("active");
		$('#allCheck').prop("checked",true);
		
	    var table = $("#inventoryTable").dataTable({ 		
			"paging": true,
		    "lengthChange": true,
		    "searching": true,
		    "ordering": true,
		    "info": true,
		});

		
// 		var toggle =1;
// 		 $("#allCheck").click(function() {
// 		        var checkBoxes = $(".selectCheck");
// 		        if(toggle == 0){
// 		        	checkBoxes.prop("checked", true);
// 		        	toggle = 1;
// 		        }
// 		        else{
// 		        	checkBoxes.prop("checked", false);
// 		        	$(table.fnGetHiddenNodes()).find('input').appendTo(this);
// 		        	toggle = 0;
// 		        }
// 		    });
	
		 $('#allCheck').change(function(){
			    var cells = table.cells( ).nodes();
			   
			    $( cells ).find(':checkbox').prop('checked', $(this).is(':checked'));
			});
		 
		 $("#formSubmit").click(function(event) {
			 	event.preventDefault();
		        $(table.fnGetHiddenNodes()).find('input:checked').appendTo(this);
		        $("#dispatchForm").submit();
		    } );
		 
 
// 		 $("#liqCat").change(function () {
// 				var cat = $("#liqCat").val();

// 				if (cat != null) {
// 					for ( var i = 0; i < cat.length; i++) {
// 						$('#tbody tr').each(function() {
// 							$(this).hide();
// 						});

// 						if (cat[i] == "all") {
// 									    if no filters selected, show all items
// 							$('#tbody tr').each(function() {
// 								$(this).show();
// 							});
// 						} else {
// 									    otherwise, hide everything...
// 							$('#tbody tr').each(function() {
// 								var value = $(this).find("td:eq(5)").html();
// 								value = value.replace("&amp;", "&");
// 										    	alert(value+" : "+cat);
// 								if (value == cat[i]) {
// 									$(this).show();
// 								}

// 							});
// 						}
// 					}
// 				} else {
// 					$('#tbody tr').each(function() {
// 						$(this).show();
// 					});
// 				}
// 			});

		 	
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
                 <form method="post"  action="<c:url value="/Dispatch/getLot"/>">
        		  
                  <div class="col-xs-6">
                  	<div class="box-body">
                  	
                  	 <div class="form-group">
                     <label for="barcode">Enter Lot Name</label>
                      <input class="form-control col-xs-3-offset-6 required" name="lotName" id="lotName" required ="true"/>
                    </div>
                      <div class="form-group">
                      <input class="btn btn-primary"  type="submit" value="Submit">
                    </div>
                    
				</div>
				</div>
			</form>
			</div>
			</div>
			<div class="box-footer"></div>
			<br>
			
			<c:if test="${not empty show}">
			<form name="catForm" id="catForm" method="post" action="<c:url value="/Dispatch/filterCategory"/>">
			<div class="col-xs-12" >
                    <input type="hidden" value="${lotId}" name="lotId"/>                
                    <label>Search Liquidation Category</label>&nbsp;&nbsp;&nbsp;
                    <div class="chosen-container-multi chosen-container " style="width: 250px;">
                    <select name="liqCat" multiple="multiple" id ="liqCat"
								 class="form-control" data-rel="chosen" ">
                    <c:forEach var="cat" items="${liqCat}">
                 			<option value="${cat}">${cat}</option>
                 		</c:forEach>
                    </select>
                  	</div>
                  	  <input type="submit" value="Filter" class="btn btn-primary"/>
                  
           	 </div>
          	</form>	
                 <br><br><br>
                 <div class="box-body">
                 
                 
                 <form name="dispatchForm" id="dispatchForm" method="post" action="<c:url value="/Dispatch/buyerDetails"/>">
           			<input type="hidden" value="${lotId}" name="lotId" />
         
           			<table class="table table-bordered table-striped table-hover form-group " id="inventoryTable">
           			<thead>
           				<tr>
           				<th>Code</th>
           				<th>SUPC</th>
           				<th>Lot Name</th>
           				<th>Product Name</th>
           				<th>Price</th>
           				<th>Liquidation Category</th>
           				<th>Status</th>
           				<th></th>
<!--            				<th>Select All <input type="checkbox" id="allCheck" /></th> -->
           				</tr>
           			</thead>

           			<tbody id="tbody">
           			<c:forEach var="disinv" items="${dispatchList}">
           			<tr>
           				<td>${disinv.inventory.barcode}</td>
           				<td>${disinv.inventory.supc}</td>
           				<td>${disinv.inventory.lot.lotName}</td>
           				<td>${disinv.inventory.productName}</td>
           				<td>${disinv.inventory.price}</td>
           				<td>${disinv.inventory.liqCategory}</td>
           				<td>${disinv.inventory.status}</td>
           				<td><input type="checkbox" class="selectCheck" name="check"  checked value="${disinv.inventory.id}"/>
           				</td>
           			</tr>
           			</c:forEach>
           			</tbody>
           			</table>
           			<br><br>
           			<div class="form-group" align="center">
					<input type="submit" class="btn btn-primary" id="formSubmit" value="Forward To Dispatch">
                    </div>
           			</form>    	 			
				</div>
			
		  </c:if>
		  
		  </div>
		</div></div>
</jsp:body>
</tags:page>