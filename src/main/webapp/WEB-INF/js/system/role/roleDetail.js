var roleId;
$(function() {
	roleId = $("#id").val();
	$("#user_datagrid").datagrid({
		method : 'POST',
		url : contextPath + '/role/userDatagrid?roleId=' + roleId,
		fit : true,
		loadMsg : '数据加载中.....',
		pagination : true,//分页控件
		rownumbers : true,//行号 
		fitcolumns : true,
		columns : [ [ {
			field : 'id',
			checkbox : true
		}, {
			field : 'username',
			title : '姓名',
			width : '50%'
		}, {
			field : 'sysOrganization.id',
			title : '组织机构',
			width : '45%',
			formatter : function(value, row, index) {
				if (row.sysOrganization) {
					return row.sysOrganization.name;
				} else {
					return value;
				}
			}
		} ] ],
		toolbar : '#toolbar_resource_user'
	});
	
	//设置分页控件 
    var p = $('#user_datagrid').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [10,20,50],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
    });

	// 权限菜单
	$("#treegrid_resource").treegrid({
		iconCls : 'icon-ok',
		height : 500,
		rownumbers : true,
		animate : true,
		collapsible : true,
		fit : true,
		fitColumns : true,
		singleSelect : false,
		cascadeCheck : true,
		url : contextPath + '/role/resourceTG?roleId=' + roleId,
		method : 'post',
		idField : 'id',
		treeField : 'name',
		showFooter : true,
		columns : [ [ {
			field : 'id',
			checkbox : true,
			width : '10%'
		}, {
			title : '菜单名',
			field : 'name',
			width : '30%'
		}, {
			field : 'url',
			title : '连接地址',
			width : '35%',
			align : 'right'
		}, {
			field : 'permission',
			title : '功能权限',
			width : '25%'
		} ] ],
		onLoadSuccess : function(node, data) {
			getTGDefaultState(data);
		},
		toolbar : '#toolbar_role_resource'
	});

	function getTGDefaultState(data) {
		if (data) {
			$.each(data, function(j, o) {
				if (o.isChecked != null && o.isChecked == true) {
					$("#treegrid_resource").treegrid('select', o.id);
				}
				if (o.children) {
					getTGDefaultState(o.children);
				}
			});
		}
	}

	// 权限机构treegrid
	$("#treegrid_org").treegrid({
		iconCls : 'icon-ok',
		height : 500,
		rownumbers : true,
		animate : true,
		collapsible : true,
		fit : true,
		singleSelect : false,
		url : contextPath + '/role/orgTG?roleId=' + roleId,
		method : 'post',
		idField : 'id',
		treeField : 'name',
		showFooter : true,
		columns : [ [ {
			field : 'id',
			checkbox : true
		}, {
			title : '机构名称',
			field : 'name',
			width : '90%'
		} ] ],
		onLoadSuccess : function(node, data) {
			getOTGDefaultState(data);
		},
		toolbar : '#toolbar_roleorg'
	});
	
	function getOTGDefaultState(data) {
		if (data) {
			$.each(data, function(j, o) {
				if (o.isChecked != null && o.isChecked == true) {
					$("#treegrid_org").treegrid('select', o.id);
				}
				if (o.children) {
					getOTGDefaultState(o.children);
				}
			});
		}
	}
});

function saveRoleOrg() {
	var data = $("#treegrid_org").treegrid('getChecked');
	var str = "";
	if (data) {
		$.each(data, function(j, o) {
			str += 'sysOrganizations[' + j + '].id=' + o['id'] + '&';
		});
	}
	str += $("#att_form :validInput").serialize();
	$.cajax({
		url : contextPath + "/role/saveRoleOrg",
		type : 'POST',
		data : str,
		success : function(result) {
			$("#treegrid_org").datagrid('reload');
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
		}
	});
}

function addUser() {
	window.top.$("#dlg").dialog({
		title : '角色列表',
		style : '{padding:0}',
		width : 700,
		height : 500,
		closed : true,
		cache : false,
		href : contextPath + '/role/userAdd?roleId=' + roleId,
		modal : true,
		onMove : function(left, top) {
			if(top < 0) {
				$(this).dialog('move',{
					left: left,
					top: 0
				});
			}
		},
		onClose : function() {
			$("#user_datagrid").datagrid('reload');
		}
	});
	window.top.$("#dlg").dialog('open');
}

function removeUser() {
	var data = $("#user_datagrid").datagrid('getChecked');
	var str = "";
	if (data) {
		$.each(data, function(j, o) {
			str += 'sysUsers[' + j + '].id=' + o['id'] + '&';
		});
	}
	str += $("#att_form :validInput").serialize();
	$.cajax({
		url : contextPath + "/role/removeUserFromRole",
		type : 'get',
		data : str,
		success : function(result) {
			$("#user_datagrid").datagrid('reload');
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
		}
	});
}

function saveRoleResource() {
	var data = $("#treegrid_resource").treegrid('getChecked');
	var str = "";
	if (data) {
		$.each(data, function(j, o) {
			str += 'sysResources[' + j + '].id=' + o['id'] + '&';
		});
	}
	str += $("#att_form :validInput").serialize();
	$.cajax({
		url : contextPath + '/role/saveRoleResource',
		type : 'get',
		data : str,
		success : function(result) {
			$("#treegrid_resource").datagrid('reload');
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
		}
	});
}

function createNewRole() {
	var data = $("#att_form :validInput").serialize();
	$.cajax({
		url : contextPath + "/role/createNewRole",
		type : 'post',
		data : data,
		success : function(result) {
			parent.$("#role_tree").tree('reload');
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
		}
	});
}
