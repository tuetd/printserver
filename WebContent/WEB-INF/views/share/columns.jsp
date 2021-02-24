<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<display:column style="width:15px;" class="checkboxclass" title="#" media="html">
	<input type="checkbox" id="checkbox${data.id}" name="checkbox"
			value="${data.id}" tabindex="1"/>
</display:column>

<%-- <display:column style="width:20px;"  property="id" title="ID" sortable="true" media="html" /> --%>
<display:column property="branchdesc" title="Branch" sortable="true" />
<display:column property="lastRo" title="RO" sortable="true" />
<display:column property="agreementno" title="Agreement No" sortable="true" />
<display:column property="disbursaldate" title="Disb. Date" sortable="true" format="{0,date,dd/MM/yyyy}" />
<display:column property="appFormno" title="App Form No" sortable="true" media="html excel pdf" />
<display:column property="customername" title="Customer Name" sortable="true" media="html"/>
<display:column property="cusName" title="Customer Name" sortable="true" media="excel pdf"/>
<%-- <display:column  property="panNo" title="National id" sortable="true" />
<display:column  property="amtRequested" title="Loan Amount" sortable="true" format="{0,number,0,000}" />
<display:column property="creditShield" title="Credit Shield" sortable="true" format="{0,number,0,000}"/>
<display:column property="tenure" title="Tenure" sortable="true" />--%>

<display:column style="width:15px;" title="CS" media="html excel pdf">
	<c:if test="${data.creditShield > 0}">Y</c:if>	
</display:column>

<display:column property="area" title="Area" sortable="true" />
<%-- <display:column style="width:2%;" title="" media="html">
	<img onclick="javascript:goViewDetail('${data.id}');return false;"
		alt="click to view detail record" style="cursor:pointer;"
		src="<%=request.getContextPath()%>/images/common/view.png" />
</display:column> --%>