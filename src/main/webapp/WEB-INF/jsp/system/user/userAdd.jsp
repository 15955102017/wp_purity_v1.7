<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/user/userAdd.js"></script>
 <div id="roleauthority-center" onSelect="onSelectTab"
			class="easyui-tabs" fit="true" plain="true" border="false">
			<!--基本信息begin-->
			<div id="tab_workerinfo" title="基本信息"  >
				<!-- 基本信息布局begin -->
				<div id="cc" class="easyui-layout" data-options="split:false,border:true,fit:true">
					<!-- 按钮部分 begin -->
					<div region="north" data-options="split:false,border:false"
						style="padding: 1px; background: #F4F4F4; height: 30px;">
						<a class="easyui-linkbutton" id="adduser_but" iconCls="icon-save"
							href="javascript:void(1)" plain="true" onclick="userRoleSave()">保存</a>
					</div>
					<!-- 按钮部分 end -->
					<!-- 角色设置 begin -->
					<div region="east" iconCls="icon-role01" title="角色设置"
						data-options="split:false,border:true,collapsible:false"
						style="width: 200px;">
						<div id="userRole_tree"></div>
					</div>
					<!-- 角色设置end -->
					<!-- 基本信息设置 begin -->
					<div region="center" iconCls="icon-user" title="基本信息"
						data-options="split:false,border:true,collapsible:false">
						<form id="user_form"> 
							<table width="200" border="0" cellspacing="0" cellpadding="5"
								class="gp_maintable">
								<tr>
									<td colspan="4">
										&nbsp;
										<!-- hidden -->
										<input type="hidden" name="id" id="id"
											value="${user.id}" />
										<input type="hidden" name="effectflag" id="effectflag"
											value="${user.effectflag}" />
										<input type="hidden" name="version" id="version"
											value="${user.version}" />
										<input id="password" name="password" value="${user.password}"
											type="hidden"></input>
											
									</td>
								</tr>
								<tr>
									<td width="3%">
										&nbsp;
									</td>
									<th width="25%">
										<div align="right">
											所属机构
										</div>
									</th>
									<td width="70%">
										<div align="left">
											<input id="organization" size="20" value="${user.sysOrganization.id}"
												name="sysOrganization.id" type="text" required="true"></input>
										</div>
									</td>
									<td width="2%">
										&nbsp;
									</td>
								</tr>

								<tr>
									<td width="3%">
										&nbsp;
									</td>
									<th width="25%">
										<div align="right">
											账号
										</div>
									</th>
									<td width="70%">
										<div align="left">
											<input id="username" value="${user.username}" size="20"
												name="username" type="text" class="easyui-validatebox"
												required="true"></input>
										</div>
									</td>
									<td width="2%">
										&nbsp;
									</td>
								</tr>

								<tr>
									<td>
										&nbsp;
									</td>
									<th>
										<div align="right">
											用户名称
										</div>
									</th>
									<td>
										<div align="left">
											<input id="realName" value="${user.realName}"
												class="easyui-validatebox" required="true" size="20"
												name="realName" type="text"></input>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<th>
										<div align="right">
											手机号
										</div>
									</th>
									<td>
										<div align="left">
											<input id="cellphone" name="cellphone" value="${user.cellphone}"
												size="20" type="text"></input>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								
								<tr>
									<td>
										&nbsp;
									</td>
									<th>
										<div align="right">
											EMAIL
										</div>
									</th>
									<td>
										<div align="left">
											<input id="email" name="email" value="${user.email}" size="20"
												type="text"></input>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								
								<tr>
									<td>
										&nbsp;
									</td>
									<th>
										<div align="right">
											职务
										</div>
									</th>
									<td>
										<div align="left">
											<input id="EMPLOYMENT" name="EMPLOYMENT"
												value="${obj.EMPLOYMENT}" size="20" type="text" panelHeight="100"
												  required="true"></input>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<th>
										<div align="right">
										    激活状态
										</div>
									</th>
									<td>
										<div align="left">
											<input id="locked" name="locked" panelHeight="100"
											
												value="${user.locked}" size="20" type="text"
												  required="true"></input>
										</div>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
					</form>
					</div>
					<!-- 基本信息设置end -->
				</div>
				<!-- 基本信息布局end -->
			</div>
			<!-- 基本信息end-->
<!-- 菜单设置 begin -->
	<div title="菜单设置" id="tab_worker_menu">
				  <div id="menu_toolbar" class="configId">
						<ul>

							<li>
								<a href="#" id="menu_add_bt" class="easyui-linkbutton"
									iconCls="icon-save" plain="true" onclick="userResourceSave()">保存</a>
							</li>
						</ul>
					</div>

		<table id="resource_treegrid">
		</table>

	</div>
			<!-- 菜单设置 end -->

			<!-- 管辖区域设置 begin -->
			<div title="管辖区域设置" id="tab_worker_org">
				<div id="org_toolbar" class="configId">
						<ul>
							<li>
								<a href="#" id="org_save_bt" class="easyui-linkbutton"
									iconCls="icon-save" plain="true" onclick="userOrgSave()">保存</a>
							</li>
						</ul>
					</div>
					<table id="org_treegrid">
					</table>
			</div>
			<!-- 管辖区域设置end -->
		</div>