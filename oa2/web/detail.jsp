<%@ page import="de.tu_ilmenau.javaweb.bean.Dept" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门详情</title>
	</head>
	<body>
	<%--<%
		Dept dept = (Dept) request.getAttribute("dept");
	%>--%>
	<h3>欢迎${name}</h3>
		<h1>部门详情</h1>
		<hr>
		部门编号：${dept.deptno}<br>
		部门名称：${dept.dname}<br>
		部门位置：${dept.loc}<br>
		
		<input type="button" value="返回列表页面" onclick="window.history.back()"/>
	</body>
</html>