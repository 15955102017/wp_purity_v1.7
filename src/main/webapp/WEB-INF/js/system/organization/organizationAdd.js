function saveOrUpdate(){
	var data = $("#att_form :validInput").serialize(); 
	$.cajax({ 
		url: contextPath + '/organization/saveOrUpdate', 
		type: 'POST',
		data: data,
		success: function(result){
			window.top.$("#dlg").dialog('close');
			$.messager.show({ // show error message
				title : 'success',
				msg : result.msg
			});
	}});
}