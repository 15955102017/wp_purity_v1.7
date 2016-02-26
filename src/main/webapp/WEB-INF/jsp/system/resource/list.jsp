<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/resource/list.js"></script>
</head>
<body style="margin: 0px;" class="easyui-layout">
	<div region="west" title="资源列表" split="true" style="width: 150px;"
		collapsible="true">
		<div id="resource_tree"></div>
	</div>
	<!-- 功能区域end -->
	<!--中间内容begin-->
	<div region="center">
		<div id="resource_center"></div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 120px;">
		<div id="resource_add" iconCls="icon-add" onclick="appendResource()">添加菜单</div>
		<div id="resource_delete" iconCls="icon-remove" onclick="removeResource()">删除菜单</div>
	</div>
</body>
</html>