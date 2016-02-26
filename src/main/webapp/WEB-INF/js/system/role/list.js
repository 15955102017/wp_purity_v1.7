$(function() {
            $("#role_tree").tree({
				animate : true,
				url : contextPath + '/role/roleLoad',
				method : 'post',
				onContextMenu : onContextMenu,
				onClick: function(node){
					loadRight(node.id);
				}
            });
            $("#role-center").tabs({
            	title:"角色管理页面",
            	border:false,
            	fit: true
            });
});

function onContextMenu(e, row) {
	e.preventDefault();
	$(this).tree('select', row.target);
	$('#mm').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
}

function loadRight(id){
	url = "<iframe src='" + contextPath + "/role/roleDetail?roleId="+id+"' frameborder='0' scrolling='no' style='width:100%;height:98%;' />";
	$("#role-center").tabs('close','角色管理页面');
	$("#role-center").tabs('add',{
		title: '角色管理页面',
	    content: url,
	    closable: true,
	    fit: true
	});
}

function roleAdd(){
	url = "<iframe src='" + contextPath + "/role/roleAdd' frameborder='0' scrolling='no' style='width:100%;height:98%;' />";
	$("#role-center").tabs('close','角色管理页面');
	$("#role-center").tabs('add',{
		title: '角色管理页面',
	    content: url,
	    closable: true,
	    fit: true
	});
}

function roleDelete(){
	var node = $('#role_tree').tree('getSelected');
	$.ajax({ 
		url: contextPath + "/role/" + node.id + "/deleteOne", 
		type: 'get',
		success: function(result){
			sid = new Date().valueOf();
			$("#role_tree").tree('options').url = contextPath + '/role/roleLoad?sid=' + sid;
			$("#role_tree").tree('reload');
			$.messager.show({ // show error message
				title : 'success',
				msg : result
			});
	}});
}