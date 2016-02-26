<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<style>.error{color:red;}</style>
<link href="${pageContext.request.contextPath}/css/login/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/fun.base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/script.js"></script>
</head>
<body>
<div class="login">
    <div class="box png">
		<div class="logo png"></div>
		<div class="input">
			<div class="log">
			<div class="error">${error}</div>
<form action="" method="post">
				<div class="name">
					<label>用户名</label><input type="text" class="text" id="value_1" placeholder="用户名" name="username" value="<shiro:principal/>" tabindex="1">
				</div>
				<div class="pwd">
					<label>密　码</label><input type="password" class="text" id="value_2" placeholder="密码" name="password" tabindex="2">
					<!-- <input type="text" class="text" id="value_3" placeholder="记住我" name="rememberMe" tabindex="3" value="true"> -->
					<input type="submit" class="submit" tabindex="3" value="登录">
				</div>
				</form>
				<div class="tip"></div>
			</div>
		</div>
	</div>
    <div class="air-balloon ab-1 png"></div>
	<div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>

<!--[if IE 6]>
<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
<script>DD_belatedPNG.fix('.png')</script>
<![endif]-->

</body>
</html>