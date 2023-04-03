<%@ page import="de.tu_ilmeanu.javaweb.jsp.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
  // 数组对象
  String[] usernames = {"yier","bubu"};

  User u1 = new User();
  User u2 = new User();
  u1.setName("一二");
  u2.setName("布布");
  User[] users = {u1,u2};
  // 向域中存储数组对象
  request.setAttribute("nameArray", usernames);
  request.setAttribute("users",users);
%>

<%--使用EL表达式取出数组中的元素--%>
${nameArray}<br><%--取出整个数组出来，调用toString方法--%>
${nameArray[0]}&nbsp
${nameArray[1]}&nbsp
${nameArray[2]}
<hr>
${users[0].name}<br>
${users[1].name}<br>
<%--list集合也是和数组一样的取数据--%>
<%--set集合是无序不可重复的，因为没有下标，所以没法直接通过下标取set集合中的某个元素--%>
<%--对于下标越界，EL表达式直接选择不显示--%>