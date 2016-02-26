<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../../js/common/tag.jsp"%>
<html>
<head>
<%@ include file="../../../js/common/jqueryJs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/datadic/datadicDetail.js"></script>
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
					<input type="hidden" name="sysDatadic.codingname" value="${datadic.sysDatadic.codingname}" /> 
					<input type="hidden" name="version" value="${datadic.version}" /> 
					</td>
				</tr>
				<%-- <tr>
					<td width="3%">&nbsp;</td>
					<td width="25%">
						<div align="right">参数编码</div>
					</td>
					<td width="70%">
						<div align="left">${datadic.codingname}</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr> --%>
				<tr>
					<td width="3%">&nbsp;</td>
					<td width="25%">
						<div align="right">父参数编码</div>
					</td>
					<td width="70%">
						<div align="left">${datadic.sysDatadic.codingname}</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<td width="25%">
						<div align="right">参数说明</div>
					</td>
					<td width="70%">
						<div align="left">
							<input id="commnets" value="${datadic.commnets}" name="commnets"
								style="width: 200px;" type="text"></input>
						</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<td width="25%">
						<div align="right">参数名称</div>
					</td>
					<td width="70%">
						<div align="left">
							<input id="cnname" value="${datadic.cnname}" name="cnname"
								style="width: 200px;" type="text"></input>
						</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>
						<div align="right">参数序列号</div>
					</td>
					<td>
						<div align="left">
							<input id="levelno" value="${datadic.levelno}" name="levelno"
								style="width: 200px;" />

						</div>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<div align="right">参数编码</div>
					</td>
					<td>
						<div align="left">
							<input name="codingname" value="${datadic.codingname}" class="easyui-validatebox textbox" type="text"
								    data-options="required:true,missingMessage:'参数编码不能为空',invalidMessage:'参数编码重复',validType:{length:[0,12],
								    remote:['${pageContext.request.contextPath}/datadic/checkUnique','codingname']}" style="width: 200px;"></input>
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
		<a class="easyui-linkbutton" id="datadic_save" iconCls="icon-save"
			href="javascript:void(1)" onclick="saveOrUpdate()">保存</a>
	</div>
	<!--窗口底部end-->
</body>
</html>