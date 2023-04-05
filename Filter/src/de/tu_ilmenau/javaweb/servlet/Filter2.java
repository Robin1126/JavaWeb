package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 05.04.2023
 * 如果采用注解的方式添加Filter,那么比较的是类名的字典排序
 * 责任链设计模式：可以动态组合程序的调用顺序
 * 因此使用注解在Filter中就不那么好了，最好是在xml文件当中进行配置
 */
public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Filter2 方法 begin");

        chain.doFilter(request,response);

        System.out.println("Filter2 方法 end");

    }

    @Override
    public void destroy() {
    }
}
