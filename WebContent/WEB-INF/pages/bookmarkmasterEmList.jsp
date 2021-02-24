<c:if test="${ not empty Bookmarkmasterlist}">
<display:table name="Bookmarkmasterlist" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="Bookmarkmaster" pagesize="25" class="table" export="true">
			<display:column property="id" escapeXml="true" sortable="true" title="id" url="/addupdateBookmarkmaster.html" paramId="bookmarkmasterId" paramProperty="id"/>
			<display:column property="name" escapeXml="true" sortable="true" title="name" url="/addupdateBookmarkmaster.html" paramId="bookmarkmasterId" paramProperty="id"/>
			<display:column property="docid" escapeXml="true" sortable="true" title="docid" url="/addupdateBookmarkmaster.html" paramId="bookmarkmasterId" paramProperty="id"/>
			<display:column property="fieldid" escapeXml="true" sortable="true" title="fieldid" url="/addupdateBookmarkmaster.html" paramId="bookmarkmasterId" paramProperty="id"/>
			<display:column property="function" escapeXml="true" sortable="true" title="function" url="/addupdateBookmarkmaster.html" paramId="bookmarkmasterId" paramProperty="id"/>
			<display:column property="format" escapeXml="true" sortable="true" title="format" url="/addupdateBookmarkmaster.html" paramId="bookmarkmasterId" paramProperty="id"/>

</display:table>		
</c:if>