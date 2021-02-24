var tinyMCEImageList = new Array(
	<%
		String time = (String)session.getAttribute("timeToget");
		if(time == null){
	%>
	["Logo 1", "media/logo.jpg"]
	<% time="1";} else if("1".equals(time)){%>
	["Logo 1", "media/logo.jpg"],
	["Logo 2", "media/logo1.jpg"]
	<% time="2";} else if("2".equals(time)){%>
	["Logo 1", "media/logo.jpg"],
	["Logo 2", "media/logo1.jpg"],
	["Logo 3", "media/logo2.jpg"]
	<% time="3";} else if("3".equals(time)){%>
	["Logo 1", "media/logo.jpg"],
	["Logo 2", "media/logo1.jpg"],
	["Logo 3", "media/logo2.jpg"],
	["Logo 4", "media/logo3.jpg"]
	<% time="4";} else if("4".equals(time)){%>
	["Logo 1", "media/logo.jpg"],
	["Logo 2", "media/logo1.jpg"],
	["Logo 3", "media/logo2.jpg"],
	["Logo 4", "media/logo3.jpg"],
	["Logo 5", "media/logo4.jpg"]
	<% time="5";} else if("5".equals(time)){%>
	["Logo 1", "media/logo.jpg"],
	["Logo 2", "media/logo1.jpg"],
	["Logo 3", "media/logo2.jpg"],
	["Logo 4", "media/logo3.jpg"],
	["Logo 6", "media/logo5.jpg"]
	<%}
	session.setAttribute("timeToget", time);%>
);