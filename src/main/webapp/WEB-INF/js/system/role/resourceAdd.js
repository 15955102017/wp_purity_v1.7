//$(function(){
//	$("#treegrid_resource").treegrid(
//			{
//				fit: true,
//				iconCls : 'icon-ok',
//				height : 500,
//				rownumbers : true,
//				animate : true,
//				fitColumns : true,
//				url : contextPath + '/resource/listData',
//				method : 'get',
//				idField : 'id',
//				treeField : 'name',
//				showFooter : true,
//				singleSelect: false,
//				columns : [ [
//                        {
//	                        field: 'id',
//	                        checkbox:true
//                        },
//						{
//							title : '资源名称',
//							field : 'name',
//							width : 100
//						},
//						{
//							field : 'url',
//							title : '连接地址',
//							width : 60,
//							align : 'right'
//						},
//						{
//							field : 'permission',
//							title : '功能权限',
//							width : 80
//						}] ],
//						onClickRow:function(node){  
//						    //级联选择  
//						    $(this).treegrid('cascadeCheck',{
//						    	id: node.id,
//						    	deepCascade:true
//						    });  
//						},
//				        toolbar: [{
//				        	text: '保存',
//				            iconCls: 'icon-save',
//				            handler: function(){
//				            	AddResourceToRole();
//				                	   }
//				                   }]
//			});
//});
//
//function AddResourceToRole(){
//	var data = $("#treegrid_resource").treegrid('getChecked');
//	var str = "";
//	if(data){
//	    $.each(data, function(j, o) {
//	 	    str += 'sysResources[' + j +'].id=' + o.id + '&';
//	    });
//	}
//	str += $("#att_form").serialize();
//	$.ajax({ 
//		url: contextPath + "/role/saveOrUpdateRole", 
//		type: 'get',
//		data: str,
//		success: function(result){
//			$("#datagrid_menusysver").datagrid('reload');
//			$.messager.show({ // show error message
//				title : 'success',
//				msg : result
//			});
//	}});
//}