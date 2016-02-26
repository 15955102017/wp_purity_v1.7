<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/sessions/list.js"></script>
</head>
<body style="margin: 0px;" class="easyui-layout">
	<div region="north" data-options="border:false,plain:true"
		split="false" style="height: 80px; overFlow: hidden; padding: 5px">
		<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>
当前在线人数：${sessionCount}人<br/>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok',border:false">
		<table id="sessions_datagrid" title="会话列表">
		</table>
		
	</div>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="offLine()">删除</a>
	</div>

</body>
</html>