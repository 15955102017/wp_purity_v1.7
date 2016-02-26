$(function() {
	$('#role_user').searchbox({
		searcher : function(value, name) {
			alert(value + "," + name);
			$("#user-center").datagrid('load', {
				roleId :roleId,
				username :value
			});
		},
		menu : '#user_mm',
		prompt : '请输入查询条件',
		width : '50%',
		height : '100%'
	});

	$("#user-center").datagrid({
		fit : true,
		method : 'post',
		url : contextPath + '/role/userWithoutRoleId',
		pagination : true,
		queryParams: {
			roleId: roleId
		},
		columns : [ [ {
			field : 'id',
			width : '5%',
			checkbox : true
		}, {
			field : 'sysOrganization.id',
			title : '组织机构',
			formatter : function(value, row, index) {
				if (row.sysOrganization) {
					return row.sysOrganization.name;
				} else {
					return value;
				}
			},
			width : '25%'
		}, {
			field : 'username',
			title : '姓名',
			width : '25%'
		}, {
			field : 'locked',
			title : '是否有效',
			formatter : function(value, row, index) {
				if (row.locked == "E") {
					return "有效";
				} else {
					return "无效";
				}
			},
			width : '10%',
		}, {
			field : 'sysRoles',
			title : '用户权限',
			width : '35%',
			formatter : function(value, row, index) {
				if (row.sysRoles) {
					var str = '';
					$.each(row.sysRoles, function(j, o) {
						str += o.description + ',';
					});
					return str;
				} else {
					return value;
				}
			}
		} ] ],
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				AddUserToRole();
			}
		} ]
	});
	
	//设置分页控件 
    var p = $('#user-center').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [10,20,50],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
    });
});

function AddUserToRole() {
	var data = $("#user-center").datagrid('getChecked');
	var str = "";
	if (data) {
		$.each(data, function(j, o) {
			str += 'sysUsers[' + j + '].id=' + o.id + '&';
		});
	}
	str += "id=" + roleId;
	$.cajax({
		url : contextPath + "/role/saveOrUpdateRole",
		type : 'post',
		data : str,
		success : function(result) {
			window.top.$("#dlg").dialog('close');
			$.messager.show({
				title : '通知',
				msg : result.msg
			});
		}
	});
}