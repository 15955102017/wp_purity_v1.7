<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/userlog/list.js"></script>
</head>
<body id="layout" style="margin: 0px;" class="easyui-layout">
	<div region="north" data-options="border:false,plain:true"
		split="false" style="overFlow: hidden; padding: 5px">
		<form id="attr_form" method="post">
			<table>
				<tr>
					<td><span style="width:100px;text-align:right;display:inline-block;">用户名:</span></td>
					<td><input name="loginName" class="easyui-validatebox" style="width: 150px" /></td>
					<td><span style="width:100px;text-align:right;display:inline-block;">开始时间:</span></td>
					<td><input name="instantStartTime" class="easyui-datetimebox"
						style="width: 150px" /></td>
					<td><span style="width:100px;text-align:right;display:inline-block;">结束时间:</span></td>
					<td><input name="instantEndTime" class="easyui-datetimebox"
						style="width: 150px" /></td>
					<td><a style="width:100px;text-align:right;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="findUserlogDG()">查询</a></td>
					<td><a style="width:100px;text-align:right;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-undo" onclick="clearForm()">重置</a></td>
				</tr>
			</table>
		</form>
		<script>
		var nodePrefore = $("#attr_form table").html();
		</script>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok',border:false">
		<table id="userlog_datagrid" title="日志列表">
		</table>
	</div>
</body>
</html>