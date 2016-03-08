<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="ASN">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#asnsearch').addClass("active");
		
		$('#daterange').daterangepicker({
		    format: 'DD-MM-YYYY',
		    startDate: moment().subtract(29, 'days'),
		    endDate: moment(),
		    minDate: '2015-01-01',
		    maxDate: '2016-12-31',
		    dateLimit: { days: 60 },
		    showDropdowns: true,
		    showWeekNumbers: true,
		    timePicker: false,
		    timePickerIncrement: 1,
		    timePicker12Hour: true,
		    ranges: {
		       'Today': [moment(), moment()],
		       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		       'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		       'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		       'This Month': [moment().startOf('month'), moment().endOf('month')],
		       'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		    },
		    opens: 'left',
		    drops: 'down',
		    buttonClasses: ['btn', 'btn-sm'],
		    applyClass: 'btn-primary',
		    cancelClass: 'btn-default',
		    separator: ':',
		    locale: {
		        applyLabel: 'Submit',
		        cancelLabel: 'Cancel',
		        fromLabel: 'From',
		        toLabel: 'To',
		        customRangeLabel: 'Custom',
		        daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		        monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		        firstDay: 1
		    }
		}, function(start, end, label) {
		    console.log(start.toISOString(), end.toISOString(), label);
		    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		});

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
                     <label for="file">Select Date</label>
                      <input class="form-control required" name="daterange" id="daterange"/>
                    </div>
                      <div class="form-group">
                      <input class="btn btn-primary" type="submit">
                    </div>
				</div>
				</div>	
			</form></div>
			</div>
			<div class="box-footer"></div>
			</div>
			
		  <c:if test="${not empty data}">
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
           			<c:forEach var="asn" items="${data}">
           			<tr>
           				<td>${asn.barcode}</td>
           				<td>${asn.expectedDate}</td>
           				<td>${asn.isUsed}</td>
           				<td><c:if test="${not empty asn.receivedDate}">${asn.receiveDate}</c:if></td>
           			</tr>
           			</c:forEach>
           			</tbody>
           			</table>    	 			
				</div>
			</div>
		  </c:if>
		  </div>
		</div>
</jsp:body>
</tags:page>