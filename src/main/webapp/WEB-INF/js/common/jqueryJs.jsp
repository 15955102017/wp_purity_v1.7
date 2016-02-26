<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/fm.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common/index.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/extend/extend_treegrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/extend/extend_ajax.js"></script>
<script type="text/javascript">
    var contextPath = "${pageContext.request.contextPath}";
    //自定义选择器插件
    (function($) {
    	$.extend($.expr[":"], {
    		//选择器对象
    		//a:htmlDom对象，i:htmlDom对象下标，m。
    		validInput : function ( a, i, m) {
    			if(a.value != "" && a.value != undefined)
    				return true;
    		}
    	});
    })(jQuery);
</script>
<% 
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.addHeader("Cache-Control", "no-store"); //Firefox
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", -1);
    response.setDateHeader("max-age", 0);%>