<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../js/common/tag.jsp"%>
<div id="toolbar">
	<shiro:hasPermission name="user:create">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="user:update">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="user:delete">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
	</shiro:hasPermission>
</div>