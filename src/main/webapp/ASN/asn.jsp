<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#asn').addClass("active");
	});
</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
           Advance Notification
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
                 <div class="box-body">
                 <form method="post" name="uploadFile" action="<c:url value="/ASN/upload"/>">
        		  <div class="row">
                  <div class="col-xs-6">
                  	<div class="box-body">
                    <div class="form-group">
                     <label for="file">Upload File</label>
                      <input class="form-control required" name="file" id="file" type="file">
                    </div>
                    <div class="form-group">
                     <a href='<c:url value="/ASN/downloadTemplate "/>' class="btn btn-info">Download Template</a>
                    </div>
                    
                      <div class="form-group">
                      <input class="btn btn-primary" type="submit">
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