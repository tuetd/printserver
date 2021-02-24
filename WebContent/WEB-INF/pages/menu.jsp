<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="WEB-INF/classes/cssHorizontalMenu.vm">
<ul id="primary-nav" class="menuList">
    <li class="pad">&nbsp;</li>
	<menu:displayMenu name="Bookmarkmaster"/>
	<menu:displayMenu name="Datasourcemaster"/>
	<menu:displayMenu name="Docmaster"/>
	<menu:displayMenu name="EmailSysMonitorM"/>
	<menu:displayMenu name="EmailUserSysMonitor"/>
	<menu:displayMenu name="Fieldmaster"/>
	<menu:displayMenu name="Fieldtypemaster"/>
	<menu:displayMenu name="Filemaster"/>
	<menu:displayMenu name="Fileparamfilelink"/>
	<menu:displayMenu name="Fileparammaster"/>
	<menu:displayMenu name="Filetypemaster"/>
	<menu:displayMenu name="OpenOfficeTraffic"/>
	<menu:displayMenu name="Parammaster"/>
	<menu:displayMenu name="Projectmaster"/>
	<menu:displayMenu name="Recordsetfieldlink"/>
	<menu:displayMenu name="Recordsetmaster"/>
	<menu:displayMenu name="Serverconfig"/>
	<menu:displayMenu name="Sqlparammaster"/>
	<menu:displayMenu name="Sqlquerymaster"/>
	<menu:displayMenu name="Statusmaster"/>
	<menu:displayMenu name="Systemtypemaster"/>
	<menu:displayMenu name="UserRoleM"/>
	<menu:displayMenu name="Userdocprinter"/>
	<menu:displayMenu name="Usermaster"/>
	<menu:displayMenu name="Userparamlink"/>
	<menu:displayMenu name="Userparammaster"/>
	<menu:displayMenu name="Userrecordsetprintedlink"/>
</ul>
</menu:useMenuDisplayer>

<script type="text/javascript">
/*<![CDATA[*/
var navItems = document.getElementById("primary-nav").getElementsByTagName("li");

for (var i=0; i<navItems.length; i++) {
    if(navItems[i].className == "menubar") {
        navItems[i].onmouseover=function() { this.className += " over"; }
        navItems[i].onmouseout=function() { this.className = "menubar"; }
    }
}
/*]]>*/
</script>