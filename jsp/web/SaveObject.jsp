<%@ page import="de.tu_ilmeanu.javaweb.jsp.bean.User" %>
<%@ page import="de.tu_ilmeanu.javaweb.jsp.bean.Address" %>
<%@page contentType="text/html;charset=utf-8" %>

<%
  // 创建user对象
  User user = new User();
  user.setName("布布");
  user.setPassword("1126");
  user.setAge(30);

  // 将user对象存储到request对象当中
  // 每次刷新一次都会新建一个user对象
  request.setAttribute("userObj",user);
%>

<%
  // 创建Address对象
  Address addr = new Address();
  addr.setCity("韶关");
  addr.setStreet("工业西路");
  addr.setZipCode("512000");

  // 将addr和user联系起来
  user.setAddr(addr);
%>

<%--使用EL表达式，将request域当中，取出user对象，并将其输出到浏览器--%>
${userObj}
<br>
<%--如果想要获取的对象的属性值,调用的是底层的getXxx()方法，和属性的关系不大
如果有一个不是对应属性的get方法也可以执行！
get后面的名字要用小写开始，然后去掉括号--%>
<%--注意！不要在EL表达式当中添加双引号，否则就会被当作是普通的字符串输出到浏览器--%>
<%--没有对应的方法就报错，报500错误，所以get方法最好不要自己去写，使用IDEA进行生成--%>
${userObj.name}
<br>
${userObj.password}
<br>
${userObj.age}
<br>
${userObj.email}
<br>
${userObj.getEmail()}
<%--这两种方法都可以，可以直接调用方法的名字，或者直接小写get对应的属性--%>
<br>
${"一二和布布的一天"}
<br>
<hr>
${userObj.addr.city}
<br>
${userObj.addr.street}
<br>
${userObj.addr.zipCode}
<%--一路点下去就可以了,只关注get方法，不用管别的--%>

