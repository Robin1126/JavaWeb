<%--
  Created by IntelliJ IDEA.
  User: luobi
  Date: 01.04.2023
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%--不自动生成session--%>
<html>
<head>
	<title>登录页面</title>
</head>
<body>
<h1>请先进行登录</h1>
<hr>

<form ACTION="<%=request.getContextPath()%>/dept/login" method="post">
	用户名：<input type="text" name="name"><br>
	密码：<input type="password" name="pwd"><br>
	<input type="submit" value="登录" onclick="login()">
	<input type="reset" value="重置">
<script type="text/javascript">
	login = function () {
		alert("尝试登陆中...请稍后...");
	}
</script>

</form>


</body>
</html>