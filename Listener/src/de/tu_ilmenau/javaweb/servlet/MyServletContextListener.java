package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Author : Binbin Luo
 * Date : 06.04.2023
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 现在这个特殊的时刻写代码，写就是了。它会被服务器自动调用
        // 这个方法是在ServletContext对象被创建的时候调用
        System.out.println("ServletContext对象被创建了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 这个方法是ServletContext对象被销毁的时候调用
        System.out.println("ServletContext对象被销毁了");
    }
}
