$(function(){
	$("#sessions_datagrid").datagrid({
         fit: true,
         method: 'get',
         url: contextPath + '/sessions/sessionDataGrid',
         pagination: true,
         idField: 'id',
         toolbar: '#toolbar',
         columns: [[
         {
             field: 'id',
             checkbox: true,
             width: '5%'
         },{
         	 field: 'username',
             title: '用户名',
             width: '20%'
         },{
         	 field: 'host',
             title: '主机地址',
             width: '25%'
         },{
        	 field: 'startTimestamp',
        	 title: '连接时间',
        	 width: '25%',
             formatter:function(value,rowData,rowIndex){
             	var date = new Date();
             	date.setTime(value);
             	return date.toLocaleString();
             }
         },{
         	 field: 'lastAccessTime',
             title: '最新接入时间',
             width: '25%',
             formatter:function(value,rowData,rowIndex){
             	var date = new Date();
             	date.setTime(value);
             	return date.toLocaleString();
             }
         }]]
     });
});

function offLine(){
	var data = $("#sessions_datagrid").datagrid('getChecked');
	var str = "";
	if(data){
	    $.each(data, function(j, o) {
	 	    str += 'sessions=' +o.id + '&';
	    });
	}
	$.cajax({ 
		url: contextPath + "/sessions/forceLogout", 
		type: 'get',
		data: str,
		success: function(result){
			$("#sessions_datagrid").datagrid('reload');
			$.messager.show({ // show error message
				title : 'success',
				msg : result.msg
			});
	}});
}