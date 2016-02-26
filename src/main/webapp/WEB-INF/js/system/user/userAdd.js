var id = $("#id").val();
$(function() {
	$("#resource_treegrid").treegrid({
		iconCls : 'icon-ok',
		rownumbers : true,
		animate : true,
		collapsible : true,
		fit : true,
		singleSelect : false,
		url : contextPath + '/user/userResourceTG?id=' + id,
		method : 'POST',
		idField : 'id',
		treeField : 'name',
		showFooter : true,
		columns : [ [ {
			field : 'id',
			checkbox : true,
			width : '5%'
		}, {
			title : '资源名称',
			field : 'name',
			width : '30%'
		}, {
			field : 'permission',
			title : '功能权限',
			width : '25%'
		}, {
			field : 'url',
			title : '连接地址',
			width : '35%',
			align : 'right'
		} ] ],
		onLoadSuccess : function(node, data) {
			getRTGDefaultState(data);
		},
		toolbar : '#menu_toolbar'
	});

	function getRTGDefaultState(data) {
		if (data) {
			$.each(data, function(j, o) {
				if (o.isChecked != null && o.isChecked == true) {
					$("#resource_treegrid").treegrid('select', o.id);
				}
				if (o.children) {
					getRTGDefaultState(o.children);
				}
			});
		}
	}

	// 用户管理机构
	$("#org_treegrid").treegrid({
		iconCls : 'icon-ok',
		rownumbers : true,
		animate : true,
		collapsible : true,
		fit : true,
		singleSelect : false,
		url : contextPath + '/user/userOrgsTG?id=' + id,
		method : 'POST',
		idField : 'id',
		treeField : 'name',
		showFooter : true,
		columns : [ [ {
			field : 'id',
			checkbox : true,
			width : '5%'
		}, {
			title : '机构名称',
			field : 'name',
			width : '90%'
		}] ],
		onLoadSuccess : function(node, data) {
			getOTGDefaultState(data);
		},
		toolbar : '#org_toolbar'
	});

	function getOTGDefaultState(data) {
		if (data) {
			$.each(data, function(j, o) {
				if (o.isChecked != null && o.isChecked == true) {
					$("#org_treegrid").treegrid('select', o.id);
				}
				if (o.children) {
					getOTGDefaultState(o.children);
				}
			});
		}
	}

	$("#userRole_tree").tree({
		animate : true,
		checkbox : true,
		url : contextPath + '/user/userRoleT?id=' + id,
		method : 'POST'
	});

//	var orgid = $("#organization").val();

	$("#organization").combotree({
		method : 'get',
		url : contextPath + '/user/orgCombotree',
		valueField : 'id',
		textField : 'name'
	});
});

function userRoleSave() {
	var node = $("#userRole_tree").tree('getChecked');
	var str = "";
	$.each(node, function(j, o) {
		str += 'sysRoles[' + j + '].id=' + o.id + '&';
	});
	var data = $("#user_form :validInput").serialize();
	$.cajax({
		url : contextPath + "/user/saveOrUpdateUser",
		type : 'POST',
		data : str + data,
		success : function(result) {
			$("#id").val(result.user.id);
			$("#user_form input[name='version']").val(result.version);
			$("#user_datagrid").datagrid('reload');
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
		}
	});
}

function userOrgSave() {
	var node = $("#org_treegrid").treegrid('getChecked');
	var str = "";
	$.each(node, function(j, o) {
		str += 'sysOrganizations[' + j + '].id=' + o.id + '&';
	});
	var data = $("#user_form :validInput").serialize();
	$.cajax({
		url : contextPath + "/user/saveOrUpdateOrg",
		type : 'POST',
		data : str + data,
		dataType : 'json',
		success : function(result) {
			$("#user_form input[name='version']").val(result.version);
			$("#user_datagrid").datagrid('reload');
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
		}
	});
}

function userResourceSave() {
	var node = $("#resource_treegrid").treegrid('getChecked');
	var str = "";
	$.each(node, function(j, o) {
		str += 'sysResources[' + j + '].id=' + o.id + '&';
	});
	var data = $("#user_form :validInput").serialize();
	$.cajax({
		url : contextPath + "/user/saveOrUpdateResource",
		type : 'POST',
		data : str + data,
		dataType : 'json',
		success : function(result) {
			$("#user_form input[name='version']").val(result.version);
			$("#user_datagrid").datagrid('reload');
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
		}
	});
}