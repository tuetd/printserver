
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<display:column style="width:15px;" class="checkboxclass" title="#" media="html">
	<input type="checkbox" id="checkbox${data.docRefId}" name="checkbox"
			value="${data.docRefId}" tabindex="1"/>
</display:column>

<%-- <display:column style="width:20px;"  property="id" title="ID" sortable="true" media="html" /> --%>

<display:column property="cif" title="Customer Id" sortable="true" />
<display:column property="loanId" title="Loan no"  />
<display:column property="docDesc" title="Document Name" />
<display:column property="scanDate" title="Scanned Date"  format="{0,date,dd/MM/yyyy}"/>
<display:column  property="uploadChannel" title="User Department"  />
<display:column property="docRefId" title="Doc Ref ID" />


<display:column style="width:2%;" title="" media="html">
	<img onclick="openNotePopup('popup-receive-div','popupFormReceive')"
		alt="click to change status" style="cursor:pointer;"
		src="<%=request.getContextPath()%>/images/common/view.png" />
</display:column>