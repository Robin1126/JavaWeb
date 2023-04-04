<%@ page import="de.tu_ilmeanu.javaweb.jsp.bean.Student" %>
<%@ page contentType="text/html;charset=utf-8" %>

<%--
比较鸡肋，一般运算都是在java当中做的，EL只是展示出来就行
    关于EL表达式当中的运算符
        1. 算术运算符
        + - * / %
        2. 关系运算符
        = !=  < > <= >= eq
        3. 逻辑运算符
        ! && || not and or
        4. 条件运算符
         ? : 三目运算符
        5. 取值运算符
         [] 和 .
        6. empty 运算符
--%>
+号运算符 求和：不存在拼接${10 + 20}
${10 + "20"}
<br>
<%--在EL表达式当中，加号运算符只会进行运算，不会进行拼接操作--%>
<%--“20”会被自动转换成数字,如果“abc”这种就会报错，不允许--%>

<%--关系运算符--%>
${"abc"=="abc"}
<br>
<%
    Object obj = new Object();
    request.setAttribute("k1", obj);
    request.setAttribute("k2", obj);
%>
${k1 == k2}<br>

<%
    String s1 = new String("bubu");
    String s2 = new String("bubu");
    request.setAttribute("a",s1);
    request.setAttribute("b",s2);
%>
${a == b}<br>
<%--这个显示的也是true，猜测是不是调用了equals方法--%>
<%
    Object o1 = new Object();
    Object o2 = new Object();
    request.setAttribute("o1",o1);
    request.setAttribute("o2",o2);
%>
${o1 == o2}<br> <%--false--%>
<hr>
<%--测试Student类在EL表达式当中--%>
<%
    Student student1 = new Student();
    student1.setId("1126");
    student1.setName("布布");
    Student student2 = new Student();
    student2.setName("布布");
    student2.setId("1126");

    request.setAttribute("student1",student1);
    request.setAttribute("student2",student2);
%>
${student1 == student2}<br>
${student1 eq student2}<br>
<hr>
<%-- 从控制台可以看到 equals方法执行了！ == 运算符比较的就是equals方法 --%>
<%--eq也是代表的equals，同样底层也是调用了equals方法--%>
<%--！= 同样也是调用了equals方法--%>
${!(200 eq 200)}<br>
${not(200 eq 200)}
<%--这两个都是一样的，not也可以使用！--%>
<hr>
<%--empty运算符,判断是否为空，如果为空就true，否则就是false--%>
${empty param.username}<br>
<%--param是请求中的属性值--%>
${empty initParam.pageNum}
<%--上面这个在ServletContext当中，因此不为空--%>
<hr>
<%--注意！下面的运算顺序,下面是等价的，先运行的是empty，因此两个都是false--%>
${empty param.password == null}<br>
${(empty param.password) == null}<br>
<hr>
${param.password == null} <br>
<hr>
<%--条件运算符--%>
${empty param.username ? "对不起用户名不能为空" : "用户名是："}${param.username}
<%--由于不存在字符串的拼接，考虑使用两个EL表达式来完成--%>
