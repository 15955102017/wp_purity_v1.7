<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/resource/resourceDetail.js"></script>
</head>
<body class="easyui-layout" fit="true">

	<!--右边窗口begin-->
	<div region="center" border="false" style="padding: 5px;">
		<!--基础信息begin-->
		<form id="att_form" method="get">
			<table width="100%" border="0" cellspacing="0" cellpadding="5"
				class="gp_maintable">
				<tr>
					<td colspan="4">&nbsp; <!-- hidden --> 
					   <input type="hidden" name="id" value="${sysResource.id}" /> 
					   <input type="hidden" name="sysResource.id" value="${sysResource.sysResource.id}" /> 
					   <input type="hidden" name="effectflag" value="${sysResource.effectflag}" />
					   <input type="hidden" name="version" value="${sysResource.version}" />
					</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<td width="25%">
						<div align="right">菜单id</div>
					</td>
					<td width="70%">
						<div align="left">${sysResource.id}</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<td width="25%">
						<div align="right">父菜单id</div>
					</td>
					<td width="70%">
						<div align="left">${sysResource.sysResource.id}</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<td width="25%">
						<div align="right">菜单名称</div>
					</td>
					<td width="70%">
						<div align="left">
							<input id="name" value="${sysResource.name}" name="name"
								style="width: 200px;" type="text"></input>
						</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>
						<div align="right">菜单图标</div>
					</td>
					<td>
						<div align="left">
							<input id="icon" value="${sysResource.icon}" name="icon" type="text"
								style="width: 200px;"></input>

						</div>
					</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>
						<div align="right">菜单链接地址</div>
					</td>
					<td>
						<div align="left">
							<input id="url" name="url" value="${sysResource.url}"
								type="text" style="width: 600px;"></input>

						</div>
					</td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>
						<div align="right">菜单类型</div>
					</td>
					<td>
						<div align="left">
							<input id="type" name="type" value="${sysResource.type}"
								style="width: 200px;" type="text"></input>
						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<div align="right">菜单权限</div>
					</td>
					<td>
						<div align="left">

							<input value="${sysResource.permission}" name="permission"
								style="width: 200px;" />

						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<div align="right">菜单序列号</div>
					</td>
					<td>
						<div align="left">
							<input id="menuorder" value="${sysResource.menuorder}" name="menuorder"
								style="width: 200px;" />

						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>
		<!--基础信息end-->
	</div>
	<!--右边窗口begin-->
	<!--窗口底部begin-->
	<div region="south" border="false"
		style="text-align: right; height: 30px; padding: 2px; border-top: 1px solid #ccc;">
		<a class="easyui-linkbutton" id="addmenu_but" iconCls="icon-save"
			href="javascript:void(1)" onclick="saveOrUpdate()">保存</a>
	</div>
	<!--窗口底部end-->
</body>
</html>