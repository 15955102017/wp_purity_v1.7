function saveOrUpdate(){
	//验证表单是否合法
	if (!$("#attr_form").form('validate')) {
		return false;
	}
	//对表单数据进行序列化
	var data = $("#attr_form :validInput").serialize();
	//获取用户选中的树的节点
	var node = parent.$('#organization_tree').tree('getSelected');
	//ajax请求
	$.cajax({ 
		url: contextPath + '/organization/saveOrUpdate', 
		type: 'POST',
		data: data,
		success: function(result){
			parent.$("#organization_tree").tree('reload', node.target); 
			$("#attr_form input[name='version']").val(result.version);
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
	    }
	});
}