<%@ page import="de.tu_ilmenau.javaweb.bean.Dept" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<%--	第一步要花点时间将页面的跳转调试成功	--%>
	<head>
		<meta charset="utf-8">
		<title>部门列表页面</title>
	</head>
	<body>
<%--	显示登录名--%>
<h3>welcome back,<%=session.getAttribute("name")%> !</h3>
	<script type="text/javascript">
		function del(dno) {
			if(window.confirm('确认删除？')) {
				// alert('正在删除数据...');
				document.location.href = '<%= request.getContextPath() %>/dept/delete?deptno=' + dno;
				// 发送请求，使用DOM命令
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

		<%
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
		<% } %>
		</table>
		<hr>
		<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
	</body>
</html>