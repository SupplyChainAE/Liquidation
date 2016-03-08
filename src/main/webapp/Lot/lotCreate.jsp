<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Lot">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#lotcreate').addClass("active");
	});
</script>

</jsp:attribute>
	<jsp:body>
  
        <section class="content-header">
          <h1>
          Create Lot
          </h1>
        </section>
     
         <div class="row">
            <div class="col-xs-14">
              <div class="box">
<!--         		<div class="col-md-8"> -->
        		<form method="post"  action="<c:url value="/LotCreate/create"/>" role="form">
        		  <div class="row">
                  <div class="col-xs-10">
                  	<div class="box-body">
                    
<!--                     <div class="form-group"> -->
                      <input class="form-control hidden " name="id"
								id="id"  value="${lot.id }">
<%-- 					  <label >Lots Used :  ${lot.lotNumber} </label>			                    --%>
<!--                     </div>         -->
                    <div class="form-group">
                  	 	<div class="row">
                     	<div class="col-md-3">
                     		<h4>Current Lot Size : ${lot.currentSize }</h4>
                    	</div>
                    	<div class="col-md-4">
                    		<h4>Current Lot Number :  ${lot.lotNumber} </h4>
                    	</div>
                    	<div class="col-md-3">
                    		<h4 >Current Lot Type :  ${lot.type} </h4>
                    	</div>
                    	</div>
                    </div>
                     
                    <div class="form-group"> 
                   		    	 
                    	<label >Lot Type </label>
                    	<br>
                    	
                    	<div class="col-md-4" style="padding:0px"> 
                    	<select name="type" class="form-control required">
                    		<option selected disabled value="">Select Lot Type</option>
                    		<option value="RCD">RCD</option>
                    		<option value="LOT">LOT</option>
                    	</select></div>
                    	</div> 
                    <br><br>
             	  	<div class="form-group">
                    	<input type="submit"   class="btn btn-primary" value="Create New Lot">
             	 	</div>
              		
              	</div>
              	</div>
              </div>
			</form>
<!--           </div> -->
        </div>
      </div></div>

	</jsp:body>
</tags:page>
