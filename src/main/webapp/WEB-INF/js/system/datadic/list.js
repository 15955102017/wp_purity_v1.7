$(function() {
	$('#datadic_tree').tree({
		iconCls : 'icon-ok',
		animate : true,
		collapsible : true,
		url : contextPath + '/datadic/datadicTree',
		method : 'post',
		onContextMenu : onContextMenu,
		onClick : function(node) {
			loadRight(node.id);
		}
	});
	$("#datadic_center").tabs({
		title : "参数管理页面",
		border : false,
		fit : true
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

function loadRight(codingname) {
	url = "<iframe src='"
			+ contextPath
			+ "/datadic/datadicDetail?codingname="
			+ codingname
			+ "' frameborder='0' scrolling='no' style='width:100%;height:98%;' />"
	$("#datadic_center").tabs('close', '参数管理页面');
	$("#datadic_center").tabs('add', {
		title : '参数管理页面',
		method : 'post',
		content : url,
		closable : true,
		fit : true
	});
}

function datadicAdd() {
	var node = $('#datadic_tree').tree('getSelected');
	codingname = node.id;
	url = "<iframe src='"
			+ contextPath
			+ "/datadic/datadicAdd?codingname="
			+ codingname
			+ "' frameborder='0' scrolling='no' style='width:100%;height:98%;' />";
	$("#datadic_center").tabs('close', '菜单管理页面');
	$("#datadic_center").tabs('add', {
		title : '菜单管理页面',
		content : url,
		closable : true,
		fit : true
	});
}

function datadicDelete() {
	var node = $('#datadic_tree').tree('getSelected');
	var node_parent = $('#datadic_tree').tree('getParent', node.target);
	$.cajax({
		url : contextPath + "/datadic/" + node.id + "/deleteOne",
		type : 'POST',
		success : function(result) {
			if (node_parent == "") {
				$("#datadic_tree").tree('reload');
			} else {
				$("#datadic_tree").tree('reload', node_parent.target);
			}
			$.messager.show({ // show error message
				title : '通知',
				msg : result.msg
			});
		}
	});
}
// 鼠标右键菜单功能结束
