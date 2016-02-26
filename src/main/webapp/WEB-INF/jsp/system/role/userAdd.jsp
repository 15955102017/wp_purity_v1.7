<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!-- 功能区域begin -->
<script>
var roleId = ${roleId};
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/role/userAdd.js" />
<div class="easyui-layout" data-options="fit:true,border:false">
	<div region="north" split="true" collapsible="true" style="height:30px;">
		<input id="role_user">
		<div id="user_mm" style="width: 120px">
			<div data-options="name:'all',iconCls:'icon-ok'">All</div>
			<div data-options="name:'username'">用户名</div>
		</div>
	</div>
	<!-- 功能区域end -->
	<!--中间内容begin-->
	<div region="center" border="false">
		<div id="user-center"></div>
	</div>
</div>
<!--中间内容end-->
