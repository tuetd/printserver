<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<script type="text/javascript">
	function selectDocument(documentname){
		document.location = 'selectDocument.html?documentname='+documentname;
	}
</script>
<div>
<div id="twocenter-columns">
		<div class="container">
				<ul class="plans">
				   <c:forEach var="documentname" items="${documentlist}" varStatus="counter">
			        <c:choose>
			          <c:when test="${counter.count % 2 == 0}">
			            <c:set var="rowStyle" scope="page" value="plan highlight"/>
			          </c:when>
			          <c:otherwise>
			            <c:set var="rowStyle" scope="page" value="plan"/>
			          </c:otherwise>
			        </c:choose>
					<li  class="${rowStyle}" >
					<a href="<%=request.getContextPath()%>/selectDocument.html?documentname=${documentname.name_doc}">
						<span class="price price-green">V10</span>
						<div class="details">
							<h1 class="plan-title">${documentname.name_doc}</h1>
							<p class="plan-description">${documentname.description}</p>  
						</div>
						
					</a>
					</li>
			      </c:forEach>
				</ul>
			</div>
		

		
</div>
</div>




