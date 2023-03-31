<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>欢迎使用查询系统</title>
	</head>
	<body>
<%--	不能写死项目名，要动态获取根路径	--%>
<%--<%--%>
<%--	String contextPath = request.getContextPath();--%>
<%--%>--%>
<%--显然我们要连接数据库，用servlet来查询数据--%>
		<a href="<%= request.getContextPath() %>/dept/list">查看部门列表</a>
	</body>
</html>