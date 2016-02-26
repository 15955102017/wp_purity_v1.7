<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/user/userList.js"></script>
</head>
<body style="margin: 0px;" class="easyui-layout">
	<div region="north" data-options="border:false,plain:true"
		split="false" style="overFlow: hidden; padding: 5px">
		<form id="attr_form" method="post">
			<table>
				<tr>
					<td>用户名:</td>
					<td><input name="username" class="easyui-validatebox"
						style="width: 150px" onkeydown="if(event.keyCode==13)return false;"/></td>
					<!-- <td>所属组织:</td>
					<td><input name="sysOrganization.id" id="sysOrganization"
						style="width: 150px" /></td> -->
					<td><a style="width:100px;text-align:right;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="searchUser()">查询</a></td>
					<td><a style="width:100px;text-align:right;display:inline-block;" href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-undo" onclick="clearForm()">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok',border:false">
		<table id="user_datagrid" title="用户列表">
		</table>
	</div>
	<div id="toolbar">
		<shiro:hasPermission name="user:create">
			<a href="javascript:void(0)" id="btn_newUser" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="user:update">
			<a href="javascript:void(0)" id="btn_editUser" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" disabled="true" onclick="editUser()">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="user:delete">
			<a href="javascript:void(0)" id="btn_destroyUser" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" disabled="true" onclick="destroyUser()">删除</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="user:update">
			<a href="javascript:void(0)" id="btn_enableUser" class="easyui-linkbutton"
				iconCls="icon-myunlock" plain="true" disabled="true" onclick="enableUser()">激活</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="user:update">
			<a href="javascript:void(0)" id="btn_disableUser" class="easyui-linkbutton"
				iconCls="icon-lock" plain="true" disabled="true" onclick="disableUser()">冻结</a>
		</shiro:hasPermission>
	</div>

</body>
</html>