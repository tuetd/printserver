<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Iterator"%>
<%@page import="com.pruvn.tools.utils.PagingInfo"%>

<script type="text/javascript">
   jQuery().ready(function() {
        var totalPage = parseInt('${pagingInfo.totalPages}');
        var records = parseInt('${pagingInfo.itemsPerPage}');

        jQuery('#nextPage').click(function(){
            var value = jQuery('#countpage').attr('value');
            if(value.length == 0)
            {
                value = '0';
            }

            var pageNum = parseInt(value);

            if(pageNum >= totalPage)
            {
                pageNum = totalPage;
            }
            else
            {
                pageNum = pageNum + 1;
            }
            gotoPage(pageNum, records);
        });

        jQuery('#previousPage').click(function(){

            var value = jQuery('#countpage').attr('value');

            if(value.length == 0)
            {
                value = '1';
            }

            var pageNum = parseInt(value);

            if(pageNum > 1)
            {
                pageNum = pageNum - 1;
            }
            gotoPage(pageNum, records);
        });

        jQuery('#countpage').keydown(function (e){
               
            if(this.value.length == 0)
            {
                this.value = '1';
            }

            var pageNum = parseInt(this.value);

            if(pageNum >= totalPage)
            {
                pageNum = totalPage;
            }

            if(e.keyCode == 13)
            {
            	gotoPage(pageNum, records);
            }
            
        });

        jQuery('#views').change(function (){
			var records = jQuery('#views').val();
            gotoPage(1, records);
        });
    });

    function gotoPage(pageNum, records){
    	var pageUrl = '<c:url value="${pageUrl}"/>';
        if(pageUrl.indexOf("?") > 0){
        	window.location = pageUrl +'&pageNum='+pageNum+'&records='+records;
        }
        else{
        	window.location = pageUrl +'?pageNum='+pageNum+'&records='+records;
        }
    }
</script>

<div id="pager">
    Page <a href="#" id="previousPage"><img src="images/arrow_left.gif" width="16" height="16" /></a>
    <input size="1" value="${pagingInfo.currentPage}" type="text" name="countpage" id="countpage" />
    <a href="#" id="nextPage"><img src="images/arrow_right.gif" width="16" height="16" /></a>of ${pagingInfo.totalPages}
    pages | View <select name="view" id="views" >
    <%
        PagingInfo pageInfo = (PagingInfo)request.getAttribute("pagingInfo");
        for (Iterator<Integer> i = pageInfo.getListOfItemsPerPage().iterator(); i.hasNext(); )
        {
            Integer item = (Integer)i.next();
            if(item == pageInfo.getItemsPerPage())
            {
     %>
                <option selected><%=item%></option>
     <%
            }
            else
            {
     %>
                <option><%=item%></option>
     <%
            }
       }
     %>

     </select>
    per page | Total <strong>${pagingInfo.totalItems}</strong> records found
</div>