<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>

<script type="text/javascript">
    jQuery().ready(function() {
    	jQuery('#backbt').click(function(){          
            	window.location = '<c:url value="${backUrl}"/>';
        });
    });
</script>



<div class="cnttool">
        <div id="box">
			<p><c:out value="${message}"/></p>
		</div>
		 <div align="center">
                        <input class="btnLogin" id="backbt" type="button" value="Back"/>
                </div>
		
</div>	


