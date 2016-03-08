<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#useredit').addClass("active");
		$("form")
		.submit(
				function(e) {
					/* $("#form").validate(); */
					
					if($("#lcenter").val() == null || $("#role").val() == null)
					{
						e.preventDefault();
						alert("Please select role and warehouse for the user.");
						
					}
				})
	
	});
	
</script>

</jsp:attribute>
	<jsp:body>
  
        <section class="content-header">
          <h1>
            Edit User
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
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="hidden" name="password"
										value="${user.password}">
                    <div class="form-group">
                      <label for="userName">User Name</label>
                      <input class="form-control" name="userName" required="true" id="userName" placeholder="Enter Username"
											value="${user.userName}">
                    </div>
          
                                
                    
                    <div class="form-group ">
                    <div
											class="chosen-container chosen-container-multi">
                    <label>Roles</label><br/>
                    <select name="role" multiple="multiple" id ="role"
												class="form-control required" data-rel="chosen" style="width: 250px;">
                      <c:forEach var="roles" items="${roles}">
                      			<c:set var="found" value="false" />
                      			<c:forEach var="savedRole"
														items="${user.userRoles}">
									<c:if test="${roles.id eq savedRole.id}">
										<option value="${roles.id}" selected="selected">${roles.role}</option>
									<c:set var="found" value="true" />
									</c:if>
								</c:forEach>
			                      <c:if test="${not found}">
									<option value="${roles.id}">${roles.role}</option>
								  </c:if>
                      </c:forEach>
                    </select>
                    </div>
                  </div>
              
                  <div class="form-group ">
                    <label>Centres</label><br/>
                    <div  class="chosen-container-multi chosen-container">
                    <select name="lCenter" id ="lcenter" multiple="multiple" class="form-control required" data-rel="chosen" style="width: 250px;">
                     <c:forEach var="liq" items="${lCeneter}">
							<c:forEach var="savedLiq" items="${user.liquidationList}">
									<c:set var="found" value="false" />
									<c:if test="${liq.id eq savedLiq.id}">
										<option value="${liq.id}" selected="selected">${liq.name}</option>
									<c:set var="found" value="true" />
									</c:if>
								</c:forEach>
			                      <c:if test="${not found}">
									<option value="${liq.id}">${liq.name}</option>
								  </c:if>
                      </c:forEach>
                    </select>
                  	</div>
             	 </div>
             	 
             	  <div class="form-group" align="center">
                    <input type="submit" class="btn btn-success">
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
