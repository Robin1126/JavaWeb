<%@ page contentType="text/html;charset=utf-8" %>

<%--引入核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--核心标签库当中的if标签--%>
<%--test属性是必须存在的，而且它必须是boolean,相当于一个判断条件--%>
<%--如果test中的属性值是true的时候，条件成立，执行if标签当中的语句，注意的是，没有else标签，只有if标签
如果想搞个else的话，可以连续使用2个if标签--%>
<c:if test="${empty param.username}">
<h1>用户名不能为空！</h1>
</c:if>
<c:if test="${not empty param.username}">
    <h1>欢迎你！${param.username}</h1>
</c:if>
<%--if标签中的scope可以指定作用范围，用来指定var的范围--%>
<%--scope有四个值可以选：page,request,session,application var和scope都不是必须的--%>
<%--将var中的值可以存储到scope指定的域当中--%>
<hr>
<c:if test="${not empty param.username}" var="victory" scope="request">
    <h1>welcome！${param.username}</h1>
</c:if>

<hr>
<%--取出request当中的var,直接将var的值输出出来，这里的var保存的是test的属性的值，true/false--%>
${victory}