package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 05.04.2023
 * 编写类实现Filter接口
 * Filter在默认情况下会在服务器启用的时候创建，对比之下的Servlet是不会在服务器启动的时候创建的
 * Filter也是单实例的
 * 也可以使用*.do进行模糊匹配
 * /* 匹配所有的路径
 */
// 注意这里的注释是WebFilter！！！不是WebServlet
@WebFilter("*.do")
public class Filter1 implements Filter {
    public Filter1() {
        System.out.println("无参数构造方法执行");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // init方法在创建filter对象之后调用，并且只调用一次
        System.out.println("init方法执行");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // doFilter方法在用户发送请求的时候执行一次，发送N次请求，就执行N次请求，并且在这个方法当中编写过滤规则
        System.out.println("doFilter方法执行");

        // 这段代码的是让你执行下一个过滤器，如果没有过滤器就执行Servlet
        chain.doFilter(request,response);

        System.out.println("doFilter方法执行结束");
    }

    @Override
    public void destroy() {
        // destroy方法 在Filter对象被释放/销毁之前调用，并且只调用一次
        System.out.println("destroy方法执行");
    }
}
