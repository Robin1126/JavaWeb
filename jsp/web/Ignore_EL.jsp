<%@ page contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%--isELIgnored是设置是否忽略EL表达式--%>
<%--看需求忽略还是不忽略，如果不设置默认是false，不忽略--%>

<%
  request.setAttribute("name","布布");
%>

${name} <%--由于设置了EL表达式忽略，因此左边的表达式被当作了字符串--%>
<br>
<%--如果只是想忽略某个具体的EL表达式，可以在EL表达式前面加上\,所以一般不全局设置--%>
\${name}

