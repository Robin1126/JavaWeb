<%@ page contentType="text/html;charset=utf-8" %>

<%
  // 四个域都存储了数据，且数据的名相同，取出来的优先级？
  pageContext.setAttribute("data","pageContext");
  request.setAttribute("data","request");
  session.setAttribute("data","session");
  application.setAttribute("data","application");
%>

<%--取出data,这里取出来的是pageContext，因此可以知道EL表达式是从最小的应用域开始取的--%>
<%--范围：pageContext < request < session < application--%>
<%--在EL表达式中可以指定范围来读取数据--%>
<%--EL表达式当中有四个隐含的隐式范围对象--%>
<%--pageScope requestScope sessionScope applicationScope--%>
<%--EL表达式更加适合做页面展示，因为使用<%=%>对于无法取出的元素，会展示null--%>
<%--EL表达式会从4个scope中寻找，如果一直找不到，就不会输出--%>
${pageScope.data}
<br>
${requestScope.data}
<br>
${sessionScope.data}
<br>
${applicationScope.data}
<%--不同的scope取出不同的data--%>
<br>
<%--最后还是转换成java代码运行--%>
${usernam} 这个EL表达式等同于这一段java代码：<%=request.getAttribute("usenam") == null ? "" : request.getAttribute("usernam")%>

