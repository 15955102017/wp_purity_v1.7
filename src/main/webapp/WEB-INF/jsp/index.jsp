<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../js/common/tag.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>系统首页</title>
<%@ include file="../js/common/jqueryJs.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/index/index.js"></script>
<script type="text/javascript">
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭登录窗口
	function closePwd() {
		$('#w').window('close');
	}

	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(
				msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		})

	}

	$(function() {
		openPwd();

		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
		})

		$('#btnCancel').click(function() {
			closePwd();
		})

		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					location.href = '/ajax/loginout.ashx';
				}
			});
		})
	});
	
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img
				src="${pageContext.request.contextPath}/image/index/noscript.gif"
				alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<%-- 	<div data-options="region:'north',border:false"
		style="overflow: hidden; height: 120px; background: url(${pageContext.request.contextPath}/image/index/logo.jpg) #7f99be repeat-y center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="float: right; padding-right: 20px; padding-bottom: 10px;" class="head">欢迎
			[<shiro:principal/>] <a href="#" id="editpass">修改密码</a> <a href="${pageContext.request.contextPath}/logout">退出</a>
		</span> 
	</div> --%>
    <div id="dlg"></div>
	<!--头部开始 -->
	<div data-options="region:'north',border:false"
		style="height: 90px; overFlow: hidden; background: #B3DFDA; padding: 0px">

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="26"
					background="<c:url value='/image/index/main_03.gif' /> ">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="226" height="26"
								background="<c:url value='/image/index/main_01.gif' />">&nbsp;</td>
							<td>&nbsp;</td>
							<td width="60"><img
								src="<c:url value='/image/index/main_05.gif' />" width="60"
								height="26" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="64">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="227" height="64"
								background="<c:url value='/image/index/logo.jpg' />"
								nowrap="nowrap">&nbsp;</td>
							<td><table width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<tr>
										<td height="26"
											background="<c:url value='/image/index/main_07.gif' />">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="26">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="640" height="26"><table width="100%"
																		border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td width="30"><div align="center">
																					<img src="<c:url value='/image/index/top_1.gif' /> "
																						width="14" height="14" />
																				</div></td>
																			<td nowrap="nowrap" class="STYLE1">当前登录用户： <span
																				id="sys_user"><shiro:principal /></span>
																			</td>
																		</tr>
																	</table></td>
																<td width="19"><img
																	src="<c:url value='/image/index/main_09.gif' /> "
																	width="19" height="26" /></td>
																<td width="352">
																	<table width="46%" border="0" align="right"
																		cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="85">
																				<table width="85" border="0" cellspacing="0"
																					cellpadding="0">
																					<tr>
																						<td width="22"><div align="center">
																								<img
																									src="<c:url value='/image/index/top_2.gif' /> "
																									width="14" height="14" />
																							</div></td>
																						<td width="80"><a
																							href="${pageContext.request.contextPath}/">返回首页</a></td>
																					</tr>
																				</table>
																			</td>
																			<td width="85">
																				<table width="85" border="0" cellspacing="0"
																					cellpadding="0">
																					<tr>
																						<td width="21"><div align="center">
																								<img
																									src="<c:url value='/image/index/top_6.gif' /> "
																									width="14" height="14" />
																							</div></td>
																						<td width="75"><a href="#" id="editpass">修改密码</a></td>
																					</tr>
																				</table>
																			</td>
																			<td width="6"><img
																				src="<c:url value='/image/index/main_11.gif'  />"
																				width="6" height="26" /></td>
																			<td width="72"><table width="72" border="0"
																					cellspacing="0" cellpadding="0">
																					<tr>
																						<td width="22"><div align="center">
																								<img
																									src="<c:url value='/image/index/top_6.gif' /> "
																									width="14" height="14" />
																							</div></td>
																						<td width="80"><a href="#"
																							onclick="userpanel()">个人信息</a></td>
																					</tr>
																				</table></td>
																			<td width="10"><img
																				src="<c:url value='/image/index/main_11.gif'  />"
																				width="6" height="26" /></td>
																			<td width="56"><table width="45" border="0"
																					cellspacing="0" cellpadding="0">
																					<tr>
																						<td width="21"><div align="center">
																								<img src="<c:url value='/image/index/top_7.gif' />"
																									width="14" height="14" />
																							</div></td>
																						<td width="24"><a href="${pageContext.request.contextPath}/logout" >退出</a></td>
																					</tr>
																				</table></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
													<td width="12"><img
														src="<c:url value='/image/index/main_13.gif' />"
														width="12" height="26" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="38"
											background="<c:url value='/image/index/main_15.gif' />">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="38">&nbsp;</td>
													<td width="60"><div align="right">
															<img src="<c:url value='/image/index/main_17.gif' />"
																width="60" height="38" />
														</div></td>
												</tr>
											</table>
										</td>
									</tr>
								</table></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<!--头部结束 -->
	<!--底部开始 -->
	<div data-options="region:'south',border:false"
		style="height: 30px;; padding: 0px; text-align: center">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="22" height="30"><img
					src="<c:url value='/image/index/main_38.gif' /> " width="22"
					height="30" /></td>
				<td background="<c:url value='/image/index/main_40.gif' /> "><table
						width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="200" height="30">&nbsp;</td>
							<td><div align="center" class="STYLE1">版权所有：安徽省气象局</div></td>
							<td width="200"><div align="right" class="STYLE1">
									<div id=""></div>
								</div></td>
						</tr>
					</table></td>
				<td width="28"><img
					src="<c:url value='/image/index/main_43.gif' />  " width="28"
					height="30" /></td>
			</tr>
		</table>
	</div>
	<!--底部结束 -->
	<!-- 左侧菜单开始 -->
	<div data-options="region:'west',border:false" iconCls="icon-main01"
		split="true" style="width: 160px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',collapsible:false,border:false"
				style="width:8px; background-image:url(<c:url value='/image/index/main_29.gif' /> )">
				&nbsp;</div>
			<div
				data-options="iconCls:'icon-main01',title:'导航菜单',region:'center',collapsible:false,border:false,split:false">
				<div id="nav" class="easyui-accordion" fit="true" border="false">
				</div>
			</div>
		</div>
	</div>
	<!-- 左侧菜单结束 -->
	<!-- 中间开始 -->
	<div id="mainPanle" data-options="region:'center',border:false"
		style="padding: 0px; border-left: solid 3px #0E6F68;">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div id="tabs" class="easyui-tabs"
				data-options="fit:true,border:false"></div>
			<div data-options="region:'east',collapsible:false,border:false"
				style="width:3px; background-image:url(<c:url value='/image/index/main_29.gif' /> )">
				&nbsp;</div>
		</div>
	</div>
	<!-- 中间结束 -->
	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a id="btnCancel"
					class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>