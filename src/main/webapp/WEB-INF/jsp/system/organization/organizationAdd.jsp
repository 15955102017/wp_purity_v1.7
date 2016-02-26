<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/organization/organizationAdd.js"></script>
	<!--右边窗口begin-->
	<div region="center" border="false" style="padding: 5px;">
		<!--基础信息begin-->
		<form id="att_form" action="saveOrUpdate" method="get">
			<table width="100%" border="0" cellspacing="0" cellpadding="5"
				class="gp_maintable">
				<tr>
					<td colspan="6">&nbsp; <!-- hidden --> 
					    <input type="hidden" name="id" value="${organization.id}" /> 
						<input type="hidden" name="sysOrganization.id" value="${organization.sysOrganization.id}" />
						<input type="hidden" name="version" value="${organization.version}" /> 
					</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%" class="specalt"><div align="right">组织结构编号</div></th>
					<td width="25%"><div align="left">${organization.id}</div></td>
					<th width="22%" class="specalt"><div align="right">上级机构编号</div></th>
					<td width="25%"><div align="left">${organization.sysOrganization.id}</div></td>
					<td width="3%">&nbsp;</td>
				</tr>

				<tr>
					<td width="3%">&nbsp;</td>
					<th width="22%"><div align="right">机构名称</div></th>
					<td width="25%"><div align="left">
							<input id="name" value="${organization.name}"
								class="easyui-validatebox" required=true size="30"
								name="name" type="text"></input>
						</div></td>
					<th width="22%"><div align="right">台站号</div></th>
					<td width="25%"><div align="left">
							<input id="stationNumber" value="${organization.stationNumber}"
								class="easyui-validatebox" required=false size="30"
								name="stationNumber" type="text"></input>
						</div></td>
					<td width="3%">&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<th><div align="right">机构注册时间</div></th>
					<td>
						<div align="left">
							<input name="publishTime" class="easyui-datetimebox" value="${organization.publishTime}" size="30"
								type="text"></input>
						</div>
					</td>
					<th width="25%"><div align="right">联系电话</div></th>
					<td width="70%"><div align="left">
							<input name="orgLinkmodle" value="" size="30"
								type="text"></input>
						</div></td>
					<td>&nbsp;</td>
				</tr>

				<%-- <tr>
					<td>&nbsp;</td>
					<th><div align="right">所在地区</div></th>
					<td colspan="3">
						<div align="left">
							<input type="hidden" style="width: 280px;" name="orgAddree"
								id="orgAddree" value="${obj.orgAddree}" /> <select
								id="selProvince" required="true" name="selProvince"
								style="width: 80px;"><option value="">---请选择省--</option></select>
							<select id="selCity" required="true" name="selCity"
								style="width: 80px;"><option value="">---请选择市--</option></select>
							<select id="selArea" required="true" name="selArea"
								style="width: 80px;"><option value="">---请选择地区--</option></select>
							<input type="hidden" style="width: 280px;" name="orgProvince"
								id="orgProvince" value="${obj.orgProvince}" /> <input
								type="hidden" style="width: 280px;" name="orgCity" id="orgCity"
								value="${obj.orgCity}" /> <input type="hidden"
								style="width: 280px;" name="orgArea" id="orgArea"
								value="${obj.orgArea}" />
						</div>
					</td>

					<td>&nbsp;</td>
				</tr> --%>

				<tr>
					<td>&nbsp;</td>
					<th><div align="right">机构类型</div></th>
					<td>
						<div align="left">
							<input id="orgType" name="orgType" value="${organization.orgType}"
								size="30" type="text"></input>
						</div>
					</td>
					<th width="25%"><div align="right">机构级别</div></th>
					<td width="70%"><div align="left">
							<input id="leVel" name="leVel" value="${organization.leVel}"
								size="30" type="text"></input>
						</div></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="3%">&nbsp;</td>
					<th width="35%"><div align="right">公司简介</div></th>
					<td width="55%" colspan="3"><div align="left">
							<textarea name="comments" value="${organization.comments}" id="comments"
								class="KindEditor" style="width: 450px; height: 200px;">${organization.comments}</textarea>
						</div></td>
					<td width="2%">&nbsp;</td>
				</tr>
			</table>
		</form>
		<!--基础信息end-->
	</div>
	<!--右边窗口begin-->
	<!--窗口底部begin-->
	<div region="south" border="false"
		style="text-align: right; height: 30px; padding: 2px; border-top: 1px solid #ccc;">
		<a class="easyui-linkbutton" id="addcoding_but" iconCls="icon-save"
			href="javascript:void(1)" onclick="saveOrUpdate()">保存</a>
	</div>
