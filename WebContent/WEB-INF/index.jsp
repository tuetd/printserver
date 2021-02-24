<%-- <jsp:forward page="/items/index.html" /> --%>
<% 
response.sendRedirect(request.getContextPath() + "/items/index.html");
%>