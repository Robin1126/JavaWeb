<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="de.tu_ilmeanu.javaweb.jsp.bean.User" %>
<%@ page contentType="text/html;charset=utf-8" %>

<%
  // 一个Map集合
  Map<String,String> map = new HashMap<>();
  map.put("username","yier");
  map.put("birthday","0522");

  // 将map集合存储到request当中
  request.setAttribute("userMap", map);

  // 一个Map集合，里面的value是user对象
  Map<String, User> map2 = new HashMap<>();
  User user = new User();
  user.setName("布布");
  map2.put("user",user);
  // 存入request当中
  request.setAttribute("userMap2", map2);

%>
<%--使用EL表达式，取出map中user对象的name--%>
<%--先取出userMap2的对象，然后取出user对象，最后取出user对象中的值--%>
${userMap2.user.name}<br>
<hr>
<%--使用EL表达式，将map中的数据取出，并输出到浏览器--%>
${userMap.username}<br>
${userMap.birthday}