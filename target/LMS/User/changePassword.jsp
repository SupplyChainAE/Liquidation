<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#useredit').addClass("active");
		$('#alert').hide();
		
		
		$("#change-btn").click(function(e)
				{
					e.preventDefault();
// 					alert( $('#password').val());alert( $('#confirmpassword').val());
					
					var pass = $('#password').val();
					var confpass = $('#confirmpassword').val();
					
					if(pass == confpass)
					{	
						$("#pass-form").submit();
					}
					else
					{
						$("#alert").show();
					}
				});
	});
	
	
</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
           Change Password
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
            <div class="box-body">
              <h5>Changing Password for User : <b>${userName}</b></h5>
            </div>
					<!-- /.box-body -->
        <div class="box-footer"></div>
					<!-- /.box-header -->
                <div class="box-body">
                 	<div id="alert" class="alert alert-danger alert-dismissable">
<!--                     <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button> -->
                   Passwords Do Not Match
                    </div>
                 <form id="pass-form" method="post" action="<c:url value="/User/updatePassword"/>" role="form">
        		  <div class="row">
                  <div class="col-xs-6">
                  	<div class="box-body">
                    <div class="form-group">
                    <input type="hidden" name="id" value="${userid}" />
                      <label for="password">New Password</label>
                      <input class="form-control required" name="password"
								required ="true" id="password" placeholder="Enter new Password"
												type="password">
                    </div>
                    <div class="form-group">
                      <label for="confirmpassword">Confirm Password</label>
                      <input class="form-control required" name="confirmpassword"
						id="confirmpassword" placeholder="Confirm Password"
						required ="true"type="password">
                    </div>
                    
                     <div class="form-group">
                      <button class="btn btn-primary" id="change-btn">Change</button>
                    </div>
				</div>
				</div>
				</div>
		</form>
		</div>
		</div>
		</div>
		</div>
</jsp:body>
</tags:page>