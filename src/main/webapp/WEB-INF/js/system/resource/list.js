var url;
$(function() {
	$('#resource_tree').tree(
					{
						iconCls : 'icon-ok',
						animate : true,
						collapsible : true,
						url : contextPath + '/resource/resourceTree',
						method : 'get',
						onContextMenu : onContextMenu,
						onClick: function(node){
							loadRight(node.id);
						}
		            });
	$("#resource_center").tabs({
		            	title:"菜单管理页面",
		            	border:false,
		            	fit: true
		            });
});

	function onContextMenu(e, node) {
		e.preventDefault();
		$(this).tree('select', node.target);
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	}
	
	function loadRight(id){
		url = "<iframe src='" + contextPath + "/resource/resourceDetail?resourceId="+id+"' frameborder='0' scrolling='no' style='width:100%;height:98%;' />"
		$("#resource_center").tabs('close','菜单管理页面');
		$("#resource_center").tabs('add',{
			title: '菜单管理页面',
			method: 'get',
		    content: url,
		    closable: true,
		    fit: true
		});
	}

	//鼠标右键菜单功能开始
	function appendResource() {
		var node = $('#resource_tree').tree('getSelected');
		resourceAdd(node.id);
	}

	function resourceAdd(id){
		url = "<iframe src='" + contextPath + "/resource/resourceAdd?parentId=" + id +"' frameborder='0' scrolling='no' style='width:100%;height:98%;' />";
		$("#resource_center").tabs('close','菜单管理页面');
		$("#resource_center").tabs('add',{
			title:'菜单管理页面',
		    content: url,
		    closable:true,
		    fit: true
		});
	}

	function removeResource() {
		var node = $('#resource_tree').tree('getSelected');
		$.ajax({ 
			url: contextPath + "/resource/" + node.id + "/deleteOne", 
			type: 'get',
			success: function(result){
				$("#resource_tree").tree('reload');
				$.messager.show({ // show error message
					title : 'success',
					msg : result
				});
		    }
		});
	}
	// 鼠标右键菜单功能结束
