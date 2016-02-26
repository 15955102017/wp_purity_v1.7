$(window).resize(function () { //当浏览器大小变化时
	autoAdapt();
});

function autoAdapt() {
	var loginName = $("#attr_form input[name='loginName']").val();
	var instantStartTime = $("#attr_form input[name='instantStartTime']").val();
	var instantEndTime = $("#attr_form input[name='instantEndTime']").val();
	var winWidth = $(window).width() - 50;
	$("#attr_form table").html(nodePrefore);
	var nodeNum = $("#attr_form td").length;
	var afterWidth = new Number(0);
	var html="<tr>";
	for(var i = 0; i < nodeNum; i++) {
		var tdWidth = $("#attr_form td:eq(" + i + ")").width();
		afterWidth += tdWidth;
		if(winWidth > afterWidth) {
			html += "<td>";
			html += $("#attr_form td:eq(" + i + ")").html();
			html += "</td>";
			continue;
		} else {
			html += "</tr><tr>";
			html += "<td>";
			html += $("#attr_form td:eq(" + i + ")").html();
			html += "</td>";
			afterWidth = tdWidth;
		}
	}
	html += "</tr>";
	$("#attr_form table").html(html);
	$("#attr_form input[name='loginName']").val(loginName);
	$("#attr_form input[name='instantStartTime']").val(instantStartTime);
	$("#attr_form input[name='instantEndTime']").val(instantEndTime);
	$.parser.parse($("#attr_form"));
	var formHeight = $("#attr_form").height() + 10;
	$('#layout').layout('panel', 'north').panel('resize', {height:formHeight});
	$('#layout').layout('resize');
}

$(function() {
	autoAdapt();
            $("#userlog_datagrid").datagrid({
                height: '100%',
                method: 'post',
                url: contextPath + '/userlog/findUserlogDG',
                pagination : true,//分页控件
        		rownumbers : true,//行号 
                columns: [[{
                	field: 'id',
                	checkbox: true,
                	width: '5%'
                },{
                	field: 'loginName',
                    title: '登录账号',
                    width: '20%'
                },{
                	field: 'userName',
                    title: '姓名',
                    width: '20%'
                },{
                	field: 'createdate',
                    title: '创建时间',
                    width: '20%',
                    formatter:function(value,rowData,rowIndex){
                    	var date = new Date();
                    	date.setTime(value);
                    	return date.toLocaleString();
                    }
                },{
                	field: 'msg',
                    title: '日志信息',
                    width: '35%'
                }]],
                toolbar: '#toolbar'
            });
          //设置分页控件 
            var p = $('#userlog_datagrid').datagrid('getPager'); 
            $(p).pagination({ 
                pageSize: 10,//每页显示的记录条数，默认为10 
                pageList: [5,10,15],//可以设置每页记录条数的列表 
                beforePageText: '第',//页数文本框前显示的汉字 
                afterPageText: '页    共 {pages} 页', 
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
            }); 
});

function findUserlogDG() {
	$('#userlog_datagrid').datagrid('load',{
		loginName:$("input[name='loginName']").val(),
		instantStartTime:$("input[name='instantStartTime']").val(),
		instantEndTime:$("input[name='instantEndTime']").val()
	});
}
