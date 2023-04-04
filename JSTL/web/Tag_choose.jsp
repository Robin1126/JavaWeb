<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
    这就是 if（） else if（）... 这种方式
--%>
<c:choose>
    <c:when test="${param.age < 18}">
        <h1>少年</h1>
    </c:when>
    <c:when test="${param.age < 35}">
        <h1>青年</h1>
    </c:when>
    <c:when test="${param.age < 60}">
        <h1>中年</h1>
    </c:when>
    <c:otherwise>
        <h1>老年</h1>
    </c:otherwise>
<%--    注意！只能按照以上方式进行嵌套，默认的话是老年，因为age的值是null --%>
</c:choose>