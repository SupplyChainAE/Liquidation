<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Liquidation Center">
	<jsp:attribute name="script">
<script>





	$(document).ready(function() {
		/* $('#lcentercreate').addClass("active"); */
		/* $("#form").validate(); */
		$("form")
		.submit(
				function(e) {
					
					if ($("#name").hasClass(
							"duplicate")
							|| $("#code")
									.hasClass(
											"duplicate")) {
						e.preventDefault();
						alert("Duplicate Center Name or Code. Please correct and proceed.");
					}
				});
$("#name")
		.on(
				"change",
				function() {
					var name = $(this).val();
					var request = $
							.ajax({
								type : "POST",
								url : "/LMS/Liquidation/checkName",
								data : {
									name : name
								}
							});
					request
							.done(function(msg) {
								if (msg == "failure") {
									$("#name")
											.addClass(
													"duplicate");
									
										alert("Center Name already exists. Please Select a different name.");
								} else {
									$("#name")
											.removeClass(
													"duplicate");
								}
							});
					request
							.fail(function(jqXHR,
									textStatus) {
								alert("Failed to check");
							});
				});
$("#code")
		.on(
				"change",
				function() {
					var code = $(this).val();
					var request = $
							.ajax({
								type : "POST",
								url : "/LMS/Liquidation/checkCode",
								data : {
									code : code
								}
							});
					request
							.done(function(msg) {
								if (msg == "failure") {
									$("#code")
											.addClass(
													"duplicate");
	
									alert("Center Code already exists. Please Select a different code.");								
									
								
								} else {
									$("#code")
											.removeClass(
													"duplicate");
								}
							});
					request
							.fail(function(jqXHR,
									textStatus) {
								
								alert("Failed to check center Code.");
								
							});
				});
});
	
</script>

</jsp:attribute>
	<jsp:body>
	
		
 				 	
  
        <section class="content-header">
          <h1>
            Create Liquidation center
          </h1>
        </section>
     
         <div class="row">
            <div class="col-xs-14">
              <div class="box">
        		
        		<form method="post" name="lcenter" id="lcenter"
						action="<c:url value="/Liquidation/save"/>" role="form">
        		  <div class="row">
                  <div class="col-xs-6">
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
                		  	
                    <input class="form-control hidden"  name="id" value="${lcenter.id}"
								id="id" >
                    
                    <div class="form-group">
                      <label for="code">Code</label>
                      <input class="form-control required" name="code" value="${lcenter.code}" required="true"
								id="code" placeholder="Enter Code">
                    </div>
                    
                    <div class="form-group">
                      <label for="name">Name</label>
                      <input  class="form-control required" value="${lcenter.name}" required="true"
											name="name" id="name" placeholder="Enter Name">
                    </div>
                    
                    
<!--                     <div class="form-group"> -->
<!--                       <label for="name">Lot Size</label> -->
<%--                       <input  class="form-control required" value="${lcenter.lotSize}" required="true" --%>
<!-- 											name="lotSize" id="lotSize" placeholder="Enter Lot Size"> -->
<!--                     </div> -->
                                 	 
             	  <div class="form-group" align="Center">
             	  	
                    	<input type="submit"   class="btn btn-success">
                    
             	 </div>
              		
              	</div>
              	</div>
              </div>
			</form>
          </div>
        </div>
      </div>

	</jsp:body>
</tags:page>
