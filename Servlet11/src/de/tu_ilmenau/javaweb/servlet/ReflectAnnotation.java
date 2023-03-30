package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.annotation.WebServlet;

/**
 * Author : Binbin Luo
 * Date : 30.03.2023
 * 通过反射机制拿到注解
 */
public class ReflectAnnotation {
    public static void main(String[] args) throws ClassNotFoundException {
        // 将类上的注解进行解析
        // 获取类
        Class<?> welcomeServletClass = Class.forName("de.tu_ilmenau.javaweb.servlet.WelcomeServlet");

        // 通过类获得对象
        // 先判断这个类上面有没有注解对象
        boolean annotationPresent = welcomeServletClass.isAnnotationPresent(WebServlet.class);
//        System.out.println(annotationPresent);
        if (annotationPresent) {
            // 获取这个类上面的注解对象
            WebServlet webServletAnnotation = welcomeServletClass.getAnnotation(WebServlet.class);
            // 获取注解的value属性值
            String[] values = webServletAnnotation.value();
            // 不止可以获取value还可以获取很多的东西
            for (String value : values) {
                System.out.println(value);
            }
        }
    }
}
