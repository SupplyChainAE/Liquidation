<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Lot">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#usercreate').addClass("active");
	});
</script>

</jsp:attribute>
	<jsp:body>
  
        <section class="content-header">
          <h1>
            Update Lot Size
          </h1>
        </section>
     
         <div class="row">
            <div class="col-xs-14">
              <div class="box">
        	
        		<form method="post" name="lotSize" id="lotSize"
						action="<c:url value="/Lot/save"/>" role="form">
        		  <div class="row">
                  <div class="col-xs-6">
                  	<div class="box-body">
                    
                    <div class="form-group">
                      <input class="form-control hidden " name="id"
								id="id"  value="${lotSize.id }">
								
                      <label for="lotSize">Lot Size</label>
                      
                      <input class="form-control" name="size"
								id="size" placeholder="Enter Lot Size" value="${lotSize.size}">
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
