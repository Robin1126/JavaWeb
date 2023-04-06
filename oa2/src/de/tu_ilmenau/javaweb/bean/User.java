package de.tu_ilmenau.javaweb.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

/**
 * Author : Binbin Luo
 * Date : 06.04.2023
 */
public class User implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 用户登录了
        // User类型的对象往session存放
        // 获取servletcontext对象
        ServletContext application = event.getSession().getServletContext();

        Object onlinecount = application.getAttribute("onlinecount");
        if (onlinecount == null) {
            // 第一个用户登录的时候存储一个1进去
            application.setAttribute("onlinecount", 1);
        } else {
            int count = (Integer)onlinecount;
            count++;
            application.setAttribute("onlinecount",count);
        }

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 用户登出了
        // User类型的对象从session中去除
        ServletContext application = event.getSession().getServletContext();
        Integer onlinecount = (Integer)application.getAttribute("onlinecount");
        onlinecount--;
        application.setAttribute("onlinecount",onlinecount);
    }

    private String username;
    private String password;
    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
