<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="ASN">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#putawayupload').addClass("active");
	});
</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
           Putaway
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
                 <div class="box-body">
                 
                  	<c:if test="${error == 'true' and not empty message}">	  	
                  	<div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}
                    </div>
                  </c:if>
 				  <c:if test="${error == 'false' and not empty message}">
 				  	<div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}
                    </div>
 				  </c:if>                
                
                 <div class="row">	
                 <form method="post"  action="<c:url value="/Putaway/save"/>">
        		  
                  <div class="col-xs-6">
                  	<div class="box-body">
                  	
                  	 <div class="form-group">
                     <label for="file">Upload File</label>
                      <input class="form-control required" name="file" id="file" type="file"/>
                    </div>
                    <div class="form-group" style="float:right;" >
                     <a href='<c:url value="/Putaway/template "/>' class="btn btn-default">Download Template</a>
                    </div>
                    
                      <div class="form-group">
                      <input class="btn btn-primary" type="submit">
                    </div>
				</div>
				</div>
				
			</form></div>
			</div>
			</div>
		  </div>
		</div>
</jsp:body>
</tags:page>