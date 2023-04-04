<%@ page contentType="text/html;charset=utf-8" %>

<%--JSP中EL表达式的隐式对象
    1. pageContext
    2. param
    3. paramValues
    4. initParam
    5. 其它 (不是重点)
--%>
应用的根路径：${pageContext.request.contextPath}<br>

<%--request是JSP9大内置对象之一--%>
<%--request.getParameter("username")获取请求的参数--%>
<%--直接在地址栏中输入username=xxx来实现获取 比如 http://localhost:8080/jsp/Param.jsp?username=布布 --%>
用户名：<%=request.getParameter("username")%><br>
<%--param.属性名同样可以获取request中的属性的值--%>
用户名：${param.username}<br>

<%--假设用户提交的数据是多个怎么办？--%>
<%--比如 http://localhost:8080/jsp/Param.jsp?hobby=sing&hobby=dance&hobby=rap
显然这是用checkbox来提交的，同样一组的数据的name是一样的 param.hobby只能获得一维数组中的第一个元素--%>
<%--如果想获取所有的值，使用paramValues--%>
爱好：${param.hobby}<br>
爱好: <%=request.getParameter("hobby")%><br>
<hr>
使用paramValues来获取一维数组，加上下标来获取对应位置的元素：<br>
爱好: ${paramValues.hobby[0]},${paramValues.hobby[1]},${paramValues.hobby[2]}
<hr>

<%--EL表达式当中的隐式对象，initParam 获取Servlet上下文参数 ServletContext--%>
<%--在JSP当中，ServletContext上下文对象对应的是application--%>
上下文对象<br>
每页条数：<%=application.getInitParameter("pageSize")%><br>
页码：<%=application.getInitParameter("pageNum")%><br>
<hr>
使用EL表达式来展示：<br>
每页条数：${initParam.pageSize}<br>
页码：${initParam.pageNum}<br>