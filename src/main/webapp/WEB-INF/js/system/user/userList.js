$(function() {
            $("#user_datagrid").datagrid({
                height: '100%',
                method: 'post',
                url: contextPath + '/user/userListDG',
                pagination : true,//分页控件
        		rownumbers : true,//行号 
                columns: [[{
                	field: 'id',
                	checkbox: true,
                	width:'5%'
                },{
                	field: 'username',
                    title: '登录账号',
                    width:'20%'
                },{
                	field: 'realName',
                    title: '姓名',
                    width:'20%'
                },{
                	field: 'locked',
                    title: '是否激活',
                    width:'15%',
                    formatter: function(value,row,index){
        				if (row.locked=="E"){
        					return "有效";
        				} else {
        					return "无效";
        				}
                    }
                },{
                	field: 'cellphone',
                    title: '手机',
                    width:'20%'
                },{
                	field: 'email',
                    title: '邮箱',
                    width:'20%'
                }]],
                onCheck : function(rowIndex,rowData){
                	var data = $('#user_datagrid').datagrid('getChecked');
                	if(data.length==1){
                		$("#btn_editUser").linkbutton('enable');
                		$("#btn_destroyUser").linkbutton('enable');
                		$("#btn_enableUser").linkbutton('enable');
                		$("#btn_disableUser").linkbutton('enable');
                	} else if(data.length > 1){
                		$("#btn_editUser").linkbutton('disable');
                		$("#btn_destroyUser").linkbutton('enable');
                		$("#btn_enableUser").linkbutton('enable');
                		$("#btn_disableUser").linkbutton('enable');
                	} else {
                		$("#btn_editUser").linkbutton('disable');
                		$("#btn_destroyUser").linkbutton('disable');
                		$("#btn_enableUser").linkbutton('disable');
                		$("#btn_disableUser").linkbutton('disable');
                	}
                },
                onUncheck : function(rowIndex,rowData){
                	var data = $('#user_datagrid').datagrid('getChecked');
                	if(data.length==1){
                		$("#btn_editUser").linkbutton('enable');
                		$("#btn_destroyUser").linkbutton('enable');
                		$("#btn_enableUser").linkbutton('enable');
                		$("#btn_disableUser").linkbutton('enable');
                	} else if(data.length > 1){
                		$("#btn_editUser").linkbutton('disable');
                		$("#btn_destroyUser").linkbutton('enable');
                		$("#btn_enableUser").linkbutton('enable');
                		$("#btn_disableUser").linkbutton('enable');
                	} else {
                		$("#btn_editUser").linkbutton('disable');
                		$("#btn_destroyUser").linkbutton('disable');
                		$("#btn_enableUser").linkbutton('disable');
                		$("#btn_disableUser").linkbutton('disable');
                	}
                },
                toolbar: '#toolbar'
            });
          //设置分页控件 
            var p = $('#user_datagrid').datagrid('getPager'); 
            $(p).pagination({ 
                pageSize: 10,//每页显示的记录条数，默认为10 
                pageList: [5,10,15],//可以设置每页记录条数的列表 
                beforePageText: '第',//页数文本框前显示的汉字 
                afterPageText: '页    共 {pages} 页', 
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
            }); 
            
            $("#sysOrganization").combotree({
            	method:'get',
            	url: contextPath + '/user/orgCombotree',
            	valueField:'id',
            	textField:'name'
            	});
            
            $("#sysRoles").combobox({
            	method:'get',
            	url: contextPath + '/user/rolesLoad',
            	valueField:'id',
            	textField:'description'
            	});
        });
//查询用户信息
function searchUser() {
	var param = $("#attr_form input[value!='']").serialize();
	$('#user_datagrid').datagrid('load',{
		username:$("input[name='username']").val()
	});
}

//创建新用户
function newUser() {
	window.top.$("#dlg").dialog({
		title : '添加用户',
		style : '{padding:0}',
		width : 700,
		height : 500,
		closed : true,
		cache : false,
		href : contextPath + '/user/userAdd',
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

function editUser() {
	var row = $('#user_datagrid').datagrid('getSelected');
	if (row) {
		window.top.$("#dlg").dialog({
			title : '编辑用户',
			style : '{padding:0}',
			width : 700,
			height : 500,
			closed : true,
			cache : false,
			href: contextPath + '/user/' + row.id + '/update',
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
}

function destroyUser() {
	var data = $('#user_datagrid').datagrid('getChecked');
	var str = new Array();
	if (data) {
		$.each(data, function(j, o) {
			str.push(o.id);
		});
	}
	if (str) {
		$.messager.confirm('Confirm', '确认删除该用户?', function(r) {
					if (r) {
						$.post(contextPath + '/user/delete', {
									id : str
								}, function() {
									$('#user_datagrid').datagrid('reload'); // reload the user data
									$.messager.show({ // show error message
										title : 'success',
										msg : '删除成功！'
									});
								});
					}
				});
	}
}

//激活用户enableUser()
function enableUser() {
	var data = $('#user_datagrid').datagrid('getChecked');
	var str = new Array();
	if (data) {
		$.each(data, function(j, o) {
			str.push(o.id);
		});
	}
	$.cajax({
		url : contextPath + "/user/enableUser",
		type : 'post',
		data : {
			id : str
		},
		success : function(result) {
			$.messager.show({ // show error message
				title : 'success',
				msg : result.msg
			});
			$("#user_datagrid").datagrid('reload');
		}
	});
}

//冻结用户
function disableUser() {
	var data = $('#user_datagrid').datagrid('getChecked');
	var str = new Array();
	if (data) {
		$.each(data, function(j, o) {
			str.push(o.id);
		});
	}
	$.ajax({
		url : contextPath + "/user/disableUser",
		type : 'post',
		data : {
			id : str
		},
		success : function(result) {
			$.messager.show({ // show error message
				title : 'success',
				msg : result.msg
			});
			$("#user_datagrid").datagrid('reload');
		}
	});
}

function clearForm() {
	$('#attr_form').form('clear');
}