<%--
  Created by IntelliJ IDEA.
  User: luobi
  Date: 03.04.2023
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--在错误页面启用JSP九大内置对象之：exception--%>
<%--exception内置对象就是刚刚发生的异常对象--%>
<%--自动记录异常对象exception--%>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<h1>网络繁忙，稍后再试！！！</h1>


<%
    // 打印异常堆栈信息，输出到后台控制台。
    exception.printStackTrace();
%>
</body>
</html>
