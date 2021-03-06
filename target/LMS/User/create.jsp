<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#usercreate').addClass("active");
		$("form")
		.submit(
				function(e) {
// 					 $("#form").validate(); 
					if ($("#userName").hasClass(
							"error")) {
						e.preventDefault();
						alert("Please enter a different Username.");
						
					}
// 					if($("#lcenter").val() == null || $("#role").val() == null)
// 					{	alert($("#lcenter").val());
// 						e.preventDefault();
// 						alert("Please select role and warehouse for the user.");
						
// 					}
				})
$("#userName")
		.on(
				"change",
				function() {
					var user_name = $(this).val();
					var request = $.ajax({
						type : "POST",
						url : "/LMS/User/checkUser",
						data : {
							name : user_name
						}
					});
					request
							.done(function(msg) {
								if (msg == "failure") {
									
									alert("Username already exists. Please Select a different name.");
									
									$("#userName")
											.addClass(
													"error");
								} else {
									$("#userName")
											.removeClass(
													"error");
								}
							});
					request
							.fail(function(jqXHR,
									textStatus) {
								alert("Failed");
							});
				});
});
		
	
</script>

</jsp:attribute>
	<jsp:body>
  
        <section class="content-header">
          <h1>
            Create User
          </h1>
        </section>
     
         <div class="row">
            <div class="col-xs-14">
              <div class="box">
        	
        		<form method="post" name="user" id="user"
						action="<c:url value="/User/save"/>" role="form">
        		  <div class="row">
                  <div class="col-xs-6">
                  	<div class="box-body">
                    
                    
                    
                    
                    <div class="form-group">
                      <label for="userName">User Name</label>
                      <input class="form-control" name="userName" required ="true"
								id="userName" placeholder="Enter Username">
                    </div>
                    
                    <div class="form-group">
                      <label for="password">Password</label>
                      <input type="password" class="form-control" required ="true"
											name="password" id="password" placeholder="Password">
                    </div>
                    
                    <div class="form-group ">
                    
                    <label>Roles</label><br/>
                     <div class="chosen-container-multi chosen-container" style="width:250px;">
                    <select name="role" multiple="multiple" id ="role"
										required ="true" class="form-control required" data-rel="chosen">
                      <c:forEach var="roles" items="${roles}">
                      <option value="${roles.id}">${roles.role}</option>
                      </c:forEach>
                    </select>
                    </div>
                    
                  </div>
                  
                  <div class="form-group ">
                    
                    <label>Centers</label><br/>
                    <div class="chosen-container-multi chosen-container " style="width: 250px;">
                    <select name="lCenter" multiple="multiple" id ="lCenter"
								required ="true" class="form-control required" data-rel="chosen">
                     <c:forEach var="liq" items="${lcenter}">
                      <option value="${liq.id}">${liq.name}</option>
                      </c:forEach>
                    </select>
                  	</div>
             	 </div>
             	 
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
