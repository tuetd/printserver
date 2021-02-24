<%@ include file="/common/taglibs.jsp"%>
	<head>
		<title><fmt:message key="menu.search.usermasterList.title"/></title>
	    <content tag="heading"><fmt:message key="menu.search.usermasterList.title"/></content>
	    <meta name="menu" content="Usermaster"/>
	</head>


<div id="wrapper">
<div id="twocenter-columns">
		<form:form class="box" commandName="usermaster" action="searchUsermaster.html" method="post">
		<form:errors path="username" />
				<div id="column1test">
					<div class="rowtest">
						<div class="viewtext">
							<label>	<fmt:message key="user.search.username" /></label>
						</div>
						<div class="inputtext">
						<form:input path="username" id="username"/>
						</div>
						<div class="rowtest"></div>
					</div>
					</div>
				    <div id="column2test">
					<div class="rowtest">
						<div class="checkbutton">
							<input class="btnLogin" type="submit" name="search" value="Search" />
						</div>
					</div>
						<div class="rowtest"></div>
					</div>
			</form:form>
		</div>
		
<%@ include file="usermasterEmList.jsp" %>
</div>
