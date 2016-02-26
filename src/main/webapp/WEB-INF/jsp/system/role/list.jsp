<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/role/list.js"></script>
</head>
<body class="easyui-layout">
	<!-- 功能区域begin -->
	<div region="west" title="角色列表" split="true" style="width: 150px;"
		collapsible="true">
		<div id="role_tree"></div>
	</div>
	<!-- 功能区域end -->
	<!--中间内容begin-->
	<div region="center">
		<div id="role-center">
		</div>
	</div>
	<!--中间内容end-->
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm_addrole" iconCls="icon-add" onclick="roleAdd()">添加新角色</div>
		<div id="role_delete" iconCls="icon-remove" onclick="roleDelete()">删除角色</div>
		<div class="menu-sep"></div>
		<div id="role_refresh" iconCls="icon-reload">刷新</div>
		<div id="setmodle_properties" iconCls="icon-edit">设置为模板</div>
		<div id="role_properties" iconCls="icon-search">角色属性</div>
	</div>
</body>
</html>