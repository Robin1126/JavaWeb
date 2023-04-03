<%@ page import="de.tu_ilmeanu.javaweb.jsp.bean.User" %>
<%@ page contentType="text/html;charset=utf-8" %>

<%
    User user = new User();
    user.setName("一二");
    user.setPassword("0522");
    user.setAge(30);

    // 存储在request当中
    request.setAttribute("yier", user);
%>

<%--输出到浏览器当中--%>
<%--使用.属性名和使用中括号["属性名"]达到的效果是一致的，都是调用底层的get方法--%>
<%--中括号里面没有双引号，就是当作变量，这种使用中括号的方法基本上可以取出任何的东西，适用于存储的时候，存储的名字有特殊符号--%>
${yier.name}<br>
${yier["name"]}<br>
