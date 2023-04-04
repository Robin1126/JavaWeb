<%@ page import="de.tu_ilmenau.javaweb.jstl.bean.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=utf-8" %>
<%--引入标签库,使用taglib 的 prefix--%>
<%--tomcat10中导入的包是jakarta.servlet.jsp.jstl-2.0.0.jar 和 jakarta.setvlet.jsp.jstl-api-2.0.0.jar --%>
<%--<JSTL标签模仿的就是标签，类似html，不需要再去直接写java代码了，太难维护了
使用JSTL表面是标签,实际上底层使用的是java程序--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--这引用的是核心标签库--%>
<%--一般prefix起名都是c--%>

<%--<c:catch></c:catch>
<c:choose></c:choose>
<c:forEach></c:forEach>
<c:forTokens delims="" items=""></c:forTokens>
<c:if test=""></c:if>--%>


<%--引入格式化标签库,专门负责格式化操作的--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--引入sql标签库--%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

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
<hr>
使用Java代码for循环实现 <br>
<%--对list当中的元素遍历并且输出到浏览器--%>
<%
    List<Student> stus =(List<Student>)request.getAttribute("stulist");
    // 编写for循环,遍历集合
    for (Student stu : stus) {
        %>
    id:<%=stu.getId()%>,name:<%=stu.getName()%>
    <br>
<%
    }

%>
<hr>
使用forEach标签完成 <br>
<%--使用core标签库当中的forEach标签,对list集合进行遍历,这样就不用撕裂开java代码了--%>
<%--原理:core实际上连接的是一个tld配置文件,它是一个xml文件,这个文件描述了标签和java类之间的关系--%>
<%--items表述的是要遍历的对象,支持EL表达式--%>
<%-- stulist 输出的是一个list集合 --%>
<c:forEach items="${stulist}" var="stu">
    id:${stu.id},name:${stu.name}<br>
</c:forEach>
<%--var后面的名字是随意的,var属性代表的是集合中的每一个元素--%>
<%--当配置文件发生变动的时候,需要重新加载服务器--%>