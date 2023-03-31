<%@ page contentType="text/html;charset=UTF-8" %>

<%
    System.out.println("hello jsp");
%>

<%
    int num = 100;
%>

<%
    System.out.println("num = " + num);
    System.out.println("f = " + f);
%>

<%!
    // 放到方法之外了！
    // 一般不这么写，因为service方法外面写会存在线程安全问题，servlet是单例的
    private String name = "rose";

    public static int f = 10;

    static {
        System.out.println("静态代码块执行了！");
    }

    public void m() {
        System.out.println("m method execute! ");
    }
%>