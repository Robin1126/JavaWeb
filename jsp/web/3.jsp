<%--
  Created by IntelliJ IDEA.
  User: luobi
  Date: 31.03.2023
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 向浏览器当中输出一个变量，可以直接使用out，但是只能在service方法中用
  // JSP进行展示，servlet进行业务处理
  String name = "jack";
  System.out.println("name = " + name);
  // service方法里面有out
//  out.write("name = " + name);
  // %=会被翻译称为out.print(),注意不能添加分号
  // 有变量再这么写，没有的话直接在jsp中写
%>
<br>
<%= "bubu and yier"%>
<br>
<%= "name = " + name %>
