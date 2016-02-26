<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/datadic/list.js"></script>
</head>
<body style="margin: 0px;" class="easyui-layout">
	<div region="west" title="参数列表" split="true" style="width: 150px;"
		collapsible="true">
		<div id="datadic_tree"></div>
	</div>
	<!-- 功能区域end -->
	<!--中间内容begin-->
	<div region="center">
		<div id="datadic_center"></div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 120px;">
		<div id="datadic_add" iconCls="icon-add" onclick="datadicAdd()">添加参数</div>
		<div id="datadic_delete" iconCls="icon-remove" onclick="datadicDelete()">删除参数</div>
	</div>
</body>
</html>