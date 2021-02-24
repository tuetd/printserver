
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<display:column style="width:15px;" class="checkboxclass" title="#" media="html">
	<input type="checkbox" id="checkbox${data.id}" name="checkbox"
			value="${data.id}" tabindex="1"/>
</display:column>

<%-- <display:column style="width:20px;"  property="id" title="ID" sortable="true" media="html" /> --%>
<display:column property="bankName" title="Bank name" sortable="true" />
<display:column property="typeAutoDebit" title="Type autodebit" sortable="true" />
<display:column property="loanNo" title="Loan no" sortable="true" />
<display:column property="customerName" title="Customer name" sortable="true"  />
<display:column property="firstDueDate" title="First due date" sortable="true" format="{0,date,dd/MM/yyyy}" />
<display:column property="authorizedDate" title="Authorized date" sortable="true" format="{0,date,dd/MM/yyyy}" />
<display:column property="disbursalDate" title="Disbursal date" sortable="true" format="{0,date,dd/MM/yyyy}"/>
<display:column  property="roName" title="RO name" sortable="true" />
<display:column property="status" title="Status" sortable="true"/>
<display:column property="reason" title="Reason" sortable="true"/>
<display:column property="cm" title="CM" sortable="true"/>
<display:column property="sendDate" title="Send date" sortable="true"/>
<display:column property="sendername" title="Sender name" sortable="true"/>
<%-- <display:column style="width:2%;" title="" media="html">
	<img onclick="javascript:goViewDetail('${data.id}');return false;"
		alt="click to view detail record" style="cursor:pointer;"
		src="<%=request.getContextPath()%>/images/common/view.png" />
</display:column> --%>