package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Author : Binbin Luo
 * Date : 24.03.2023
 */

/*
        request.getParameter(String name); String 获取value这个一维数组当中的第一个元素，最常用！name是key的名字
        request.getParameterMap(); Map<String, String[]> 获取map集合
        request.getParameterNames(); Enumeration<String> 获取map集合中所有的key
        request.getParameterValues(); String[] 根据key获取Map集合的value，这个value是一个数组
 */
public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        out.print(request);
        // org.apache.catalina.connector.RequestFacade@6bcef98f 这个是Tomcat搞出来的对象
        // RequestFacade是HttpServletRequest接口
        // 同样封装了方法，我们要会使用常用方法，但是request的生命周期只在当前请求中有用。

        /*request.getParameter(String name); String 获取value这个一维数组当中的第一个元素，最常用！name是key的名字
        request.getParameterMap(); Map<String, String[]> 获取map集合
        request.getParameterNames(); Enumeration<String> 获取map集合中所有的key
        request.getParameterValues(); String[] 根据key获取Map集合的value，这个value是一个数组
         */

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // username=zhangsan&password=123&hobby=sing&hobby=dance&hobby=rap
        // 以上的数据会被Tomcat封装到request对象当中

        // 获取map集合
        Map<String, String[]> parameterMap = request.getParameterMap(); // 获取集合
        // 遍历map集合，获取所有的key，遍历
        Set<String> keys = parameterMap.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String[] values = parameterMap.get(key);
            // [Ljava.lang.String;@6adc9b6f 这就是个数组
            out.print(key + "=");
            for (String value : values
            ) {
                out.print(value + "&nbsp");
            }
            out.print("<br>");
        }

        // 还可以调用别的方法getParameterNames() 获取Map集合所有的key
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            out.print(name + "<br>");
        }

        String[] usernames = request.getParameterValues("username");
        String[] pwd = request.getParameterValues("password");
        String[] hobbys = request.getParameterValues("hobby");

        for (String username : usernames
        ) {
            out.print(username);
        }
        out.print("<br>");
        for (String password : pwd
        ) {
            out.print(password);
        }
        out.print("<br>");

        for (String hobby : hobbys
        ) {
            out.print(hobby);
            out.print("<br>");
        }

        // 通过name获得一维数组中的第一个元素
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hobby = request.getParameter("hobby");

        out.print(username + "<br>");
        out.print(password + "<br>");
        out.print(hobby + "<br>");
    }
}
