<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/role/roleDetail.js"></script>
</head>
<body class="easyui-layout">
	<!--右边窗口begin-- style=" background-color:#DBEBEA;height:71px;padding:0px;overflow:hidden;" -->
	<div region="center" border="false"
		style="padding: 2px; overflow-x: hidden;">
		<!--基础信息begin-->
		<form id="att_form" method="get">
			<table width="100%" border="0" cellspacing="0" cellpadding="5" class="gp_maintable">

				<!-- hidden -->
				<input type="hidden" id="id" name="id" value="${sysRole.id}" />
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="25%" class="specalt">
						<div align="right">角色编号</div>
					</th>
					<td width="70%">
						<div align="left">${sysRole.id}</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>

					<td width="3%">&nbsp;</td>
					<th width="25%">
						<div align="right">角色名称</div>
					</th>
					<td width="70%">
						<div align="left">
							<input id="role" value="${sysRole.role}" size="60"
								name="role" type="text"></input>
						</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>

					<td width="3%">&nbsp;</td>
					<th width="25%">
						<div align="right">备注</div>
					</th>
					<td width="70%">
						<div align="left">
							<input id="description" value="${sysRole.description}" size="60"
								name="description" type="text"></input>
						</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
			</table>
		</form>
		<!--权限tab显示部分begin-->
		<div id="p" class="easyui-panel" title="权限设置" iconCls="icon-save"
			closable="false" style="height: 420px" collapsible="false"
			minimizable="false" maximizable=false>

			<div id="roleauthority-center" class="easyui-tabs" fit="true"
				border="false">
				<div title="用户设置" id="role_work">
					<div id="toolbar_resource_user" class="configId" >
						<a href="#" id="user_add_bt" class="easyui-linkbutton"
							iconCls="icon-add" plain="true" onclick="addUser()">添加</a> <a href="#"
							id="user_delete_bt" class="easyui-linkbutton"
							iconCls="icon-remove" plain="true" onclick="removeUser()">删除</a>

					</div>
					<table id="user_datagrid">
					</table>
					<div id="dlg_user"></div>
				</div>
				<div title="菜单设置" id="role_menu">
					<div id="toolbar_role_resource" class="configId">
							<a href="#" id="menu_add_bt" class="easyui-linkbutton"
								iconCls="icon-save" plain="true" onclick="saveRoleResource()">保存</a>
					</div>

					<table id="treegrid_resource">
					</table>
				</div>
				<div title="管辖区域" id="role_org">
					<div id="toolbar_roleorg" class="configId">
							<a href="#" id="roleorg_save_bt"
								class="easyui-linkbutton" iconCls="icon-save" onclick="saveRoleOrg()">保存</a>
					</div>
					<table id="treegrid_org">
					</table>
				</div>
			</div>
			<!--权限tab显示部分end-->
		</div>
		<!--基础信息end-->
	</div>
	<!--右边窗口begin-->
	<!--窗口底部begin-->
	<div region="south" border="false"
		style="text-align: right; height: 30px; padding: 2px; border-top: 1px solid #ccc;">
		<a class="easyui-linkbutton" id="submitRole_but" iconCls="icon-save"
			href="javascript:void(1)" onclick="createNewRole()">保存</a>
	</div>
	<!--窗口底部end-->
</body>


</html>