function saveOrUpdate(){
	var data = $("#att_form :validInput").serialize(); 
	$.cajax({ 
		url: contextPath + '/resource/saveOrUpdate', 
		type: 'POST',
		data: data,
		success: function(result){
			parent.$("#resource_tree").tree('reload'); 
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
	}});
}