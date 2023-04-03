<%@ page import="java.io.PrintWriter" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%--EL表达式的格式 ${}--%>
<%--1.从某个域当中取数据 这里的域就是4个域 pageContext request session application--%>
<%--2.将取出的数据转成字符串，如果取出来的不是字符串也会转换成字符串,就是调用对象的toString方法--%>
<%--3.将字符串输出到浏览器--%>
<%
  // 向request作用域当中存储username为布布
  request.setAttribute("username","布布");
  request.setAttribute("obj",new Object());
%>

<%--将request域当中的数据取出来，并且输出到浏览器--%>
<%=request.getAttribute("username")%>
<br>
<hr>

<%--使用EL表达式来未完成功能？
EL表达式只负责取数据，不负责存数据--%>
${username}
<br>
${obj}
<%--输出object对象的地址--%>