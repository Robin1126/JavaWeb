<%@page contentType="text/html;charset=UTF-8" %>

<%
    // JSP 9大内置对象：pageContext,request,session,application,response,out,config,page,exception
    // 其中4个域对象，最小的是pageContext
    // pageContext就是页面上下文 可以通过pageContext获取什么呢？

%>
<%--从以下的代码来看，这两个是一样的--%>
<%=pageContext.getRequest()%>
<br>
<%=request%>

<%--
在EL表达式当中，没有request这个隐式对象
requestScope 这个只代表"请求范围",不等同域request对象
在EL表达式当中，有一个隐式的对象：pageContext
EL表达式中的pageContext对象和JSP中的9大内置对象pageContext是同一个对象
--%>
<hr>
<%=pageContext.getRequest()%>&nbsp
这段代码就相当于：
${pageContext.request}
<%--相当于调用了底层的getRequest方法，从而获取了request对象--%>
<br>
<%--如果想要获取应用的根路径--%>
<%=request.getContextPath()%>
和使用EL表达式是一样的：
<%--${pageContext.request.getContextPath()}--%>
${pageContext.request.contextPath}
还有使用jsp来表示
<%=((HttpServletRequest)pageContext.getRequest()).getContextPath()%>
<%--上面要注意强制转型，不然会报错，找不到对应的getContextPath方法--%>
<%--隐式对象就是隐藏的对象，不需要声明就有的，可以直接使用--%>