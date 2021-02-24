<%@ include file="../layouts/taglibs.jsp"%>
<script type="text/javascript">
	function goViewDetail(id) {
		//alert('goViewDetail id=' + id);
		return openDetailSearch('#dialog-detail-case','search_detail.html', id,'/utilities/logs.html','SEARCH');
		//window.location.href = "detail.html?id="+ id;
	}
	
	//exportlinks
</script>
<div id="container">
	
	<b>Search Loan</b>
	<c:if test="${not empty errorMessage}"> 
		<center class="errorMessage" >${errorMessage}</center>
	</c:if>
	<form:form commandName="filterRecordForm"  
		method="post" action="${pageContext.request.contextPath}/utilities/search.html">
		<%@ include file="../share/basefilter.jsp"%>
	</form:form>
	
	<display:table size="resultSize" export="none"
		cellpadding="1" cellspacing="1" id="data" name="recordList" decorator="com.pruvn.rms.utils.RecordDecorator"
		class="left tftable" requestURI="/utilities/search.html" pagesize="${pageSize}">
		<display:column property="branchdesc" title="Branch"/>
		<display:column property="lastRo" title="RO" />
		<display:column property="agreementno" title="Agreement No"/>
		<display:column property="disbursaldate" title="Disb. Date" format="{0,date,dd/MM/yyyy}" />
		<display:column property="appFormno" title="App Form No" media="html excel pdf" />
		<display:column property="customername" title="Customer Name" media="html"/>
		<display:column property="cusName" title="Customer Name" media="excel pdf"/>
		<display:column property="panNo" title="National id" />
		<display:column property="amtRequested" title="Loan Amount" format="{0,number,0,000}" />
		<display:column property="tenure" title="Tenure" />
		
		<display:column style="width:15px;" title="CS">
			<c:if test="${data.creditShield > 0}">Y</c:if>	
		</display:column>
		
		<display:column property="area" title="Area" />
	</display:table>

</div>
<%@ include file="search_detail.jsp"%>