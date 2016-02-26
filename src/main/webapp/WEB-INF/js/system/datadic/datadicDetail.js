function saveOrUpdate(){
	var data = $("#att_form :validInput").serialize(); 
	var node = parent.$('#datadic_tree').tree('getSelected');
	$.cajax({ 
		url: contextPath + '/datadic/saveOrUpdate', 
		type : 'POST',
		data: data,
		success: function(result){
			parent.$("#datadic_tree").tree('reload', node.target); 
			$("#att_form input[name='version']").val(result.version);
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
	}});
}