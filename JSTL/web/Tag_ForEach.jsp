<%@ page import="de.tu_ilmenau.javaweb.jstl.bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--var用来指定循环中的变量,这里的var是被存储到某个域当中，因为EL表达式只能从域当中取出来--%>
<%--begin开始--%>
<%--end结束--%>
<%--step步长--%>
<c:forEach var="i" begin="1" end="10" step="1">
<%--  从1开始到10结束，每隔1获取一次 for(i=1;i<=10;i++) out.print(i) --%>
  ${i}<br>
</c:forEach>
<hr>
<%
  // 创建一个list集合
  List<Student> stulist = new ArrayList<>();

  // 创建一个Student对象
  Student s1 = new Student("bubu","1126");
  Student s2 = new Student("yier","0522");
  Student s3 = new Student("lbb","11260522");

  // 添加到list集合当中
  stulist.add(s1);
  stulist.add(s2);
  stulist.add(s3);
  // 将list集合存储到request当中
  request.setAttribute("stulist",stulist);
%>
<%--varStatus=“属性” 这个属性表示var的状态对象，这是一个java对象，这个java对象代表了var的状态--%>
<%--varStatus=名字是随意的，这个此状态对象有count属性可以直接使用，count从1开始以1递增--%>
<c:forEach items="${stulist}" var="stu" varStatus="stuStatus">
<%-- stulist代表目标集合 stu代表的是集合当中的每个对象--%>
  编号:${stuStatus.count},id:${stu.id},name:${stu.name}<br>
</c:forEach>
