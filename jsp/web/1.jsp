<%--属性名=属性值 属性名=属性值 属性名=属性值--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--设置response的内容格式--%>
<%--也可以用pageEncoding="utf-8",可以不用，直接添加到charset--%>
<%@ page session="false" %>
<%--设置是否有session--%>
<%@page import="java.util.List, java.util.Date, java.util.ArrayList" %>
<%--import导包--%>
<%@page errorPage="error.jsp" %>
<%--当前页面如果出错的话，就自动跳转到error.jsp页面--%>
<%
    String name = null;
    // 空指针异常
    name.toString();
%>

<%--9大内置对象：pageContext 页面作用域 request 请求作用域 session 会话作用域 application 应用作用域 作用域对象--%>
<%--pageContext < request < session < application--%>
<%--它们都有set get remove方法--%>

<%--exception 异常对象--%>
<%--config配置对象--%>

<%--page 设定页面相关，其实就是this，当前servlet对象 --%>

<%--out 输出，response 响应 设定--%>

<%--　ServletConfig对象是Servlet对象的配置信息，是servlet对象的初始化参数。这些配置信息是通过<init-param>标签在web.xml中配置的--%>
<%--配置中同样也有name和value--%>