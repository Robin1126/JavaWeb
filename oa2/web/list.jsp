<%@ page import="de.tu_ilmenau.javaweb.bean.Dept" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%--	第一步要花点时间将页面的跳转调试成功	--%>
	<head>
		<meta charset="utf-8">
		<title>部门列表页面</title>
<%--		设置整个网页的基础路径是下面这个--%>
<%--		<base href="http://localhost:8080/oa2/">--%>
			<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
		显示协议名：${pageContext.request.scheme}<br>
		显示服务器名：${pageContext.request.serverName}<br>
		显示端口名:${pageContext.request.serverPort}<br>
		显示项目名：${pageContext.request.contextPath}<br>
	</head>
	<body>
<%--	显示登录名--%>
<h3>welcome back, ${user.username} !</h3>
<a href="dept/exit">[安全退出]</a>
	<script type="text/javascript">
		function del(dno) {
			if(window.confirm('确认删除？')) {
				// alert('正在删除数据...');
				document.location.href = '${pageContext.request.contextPath}/dept/delete?deptno=' + dno;
				// 发送请求，使用DOM命令
				// base标签不一定对js代码有效
			}
		}
	</script>
		<h1 align="center">部门列表</h1>
		<hr>
		<table border="1px" align="center" width="50%">
			<tr>
				<th>序号</th>
				<th>部门编号</th>
				<th>部门名称</th>

				<th>操作</th>
			</tr>

		<%--<%
				// 从request域中取出对象
				List<Dept> depts = (List<Dept>) request.getAttribute("depts");
				// 循环遍历
			int i = 0;
				for (Dept dept: depts) {
		%>

			<tr>
				<td><%=++i%></td>
				<td><%=dept.getDeptno()%></td>
				<td><%=dept.getDname()%></td>

				<td>
					<a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
					<a href="<%=request.getContextPath()%>/dept/detail?f=m&deptno=<%= dept.getDeptno()%>">修改</a>
					<a href="<%=request.getContextPath() %>/dept/detail?f=d&deptno=<%= dept.getDeptno()%>">详情</a>
				</td>
			</tr>
		<% } %>--%>

			<c:forEach items="${depts}" var="dept" varStatus="deptStatus">
				<tr>
					<td>${deptStatus.count}</td>
					<td>${dept.deptno}</td>
					<td>${dept.dname}</td>

					<td>
						<a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
						<a href="dept/detail?f=m&deptno=${dept.deptno}">修改</a>
						<a href="dept/detail?f=d&deptno=${dept.deptno}">详情</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
		<a href="add.jsp">新增部门</a>
		<h3>当前在线人数：${pageContext.servletContext.getAttribute("onlinecount")}</h3>
	</body>
</html>