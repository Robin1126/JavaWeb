package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Author : Binbin Luo
 * Date : 19.03.2023
 */
/*
    1. jakatra.servlet.servletConfig是规范中的一员
    ServletConfig是一个接口，（jakarta.servlet.Servlet是一个接口）

    2. 谁去实现了这个接口？
        public class servletorg.apache.catalina.core.StandardWrapperFacade implements ServletConfig{}
        结论： Tomcat服务器实现了ServletConfig接口
        如果是jetty输出ServletConfig对象的时候，不是这个结果了
            不一定一样，jetty有自己的实现类
    3. 一个Servlet对象中有一个ServletConfig对象，（Servlet和ServletConfig对象是一对一）
        100个Servlet,就应该有100个ServletConfig对象
    4. ServletConfig对象是谁创建的？ 在什么时候创建的?
        Tomcat服务器（WEB服务器）创建了ServletConfig对象
        创建servlet对象和创建ServletConfig对象几乎是同一时间完成的

    5. ServletConfig接口到底是干啥的呢？
        ServletConfig实际上是servlet的配置信息对象，因此每个servlet都有自己独有的config
        一份servlet包含一个config对象
        其实就是xml文件中servlet标签中的配置信息

    6. ServletConfig接口

    7. GenericServlet自带方法可以调用对象

    8. ServletConfig接口中有4个常用方法
        1. public String getInitParameter(String name)
        2. public Enumeration<String> getInitParameterNames();
        3. public getServletContext();
        4. public String getServletName();

    以上方法，在自己编写的Servlet类当中也可以使用this取调用，因为继承了GenericServlet类

    */
public class Config extends GenericServlet {
    /**
     *  ServletConfig是什么？
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        ServletConfig config = this.getServletConfig();
        // 父类型指向子对象
        // 输出config
        out.print("servlet" + config);
        out.print("<br>");
        // servletorg.apache.catalina.core.StandardWrapperFacade@6c51f3de

        // 获取<Servlet-name></Servlet-name>
       /* String name = config.getServletName();
        out.print("servletName = " + name );
        out.print("<br><br>");*/

        // 通过ServletConfig对象的两个方法，可以获取到web.xml文件中的初始化参数配置信息
        // java.util.Enumeration<java.lang.String> getInitParameterNames() 获取所有的初始化参数的name
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        // 遍历集合
        while (initParameterNames.hasMoreElements()) { // 是否有下一个Element
            String parameterName = initParameterNames.nextElement(); // 取元素
            String parameterValue = getInitParameter(parameterName); // 取元素对应的value
            out.print(parameterName + "=" + parameterValue);
            out.print("<br>");
        }
        // java.lang.String getInitParameter(java.lang.String name) 获取name对应的值

        // 实际上获取一个Servlet对象的初始化参数，可以不用获取ServletConfig对象，直接通过this也可以
        Enumeration<String> names = this.getInitParameterNames();

        while (names.hasMoreElements()) {
            String name = names.nextElement(); // 获取下一个Element的值
            String value = this.getInitParameter(name);
            out.print("<br>" + name + "=" + value + "<br>");
        }

        // 怎么获取ServletContext对象？
        // 第一种方式：通过ServletConfig对象获取ServletContext对象
        ServletContext application = config.getServletContext();
        // 输出Context
        out.print("<br>" + application);
        // 第二种方式：通过this也可以获取，this是继承了GenericServlet类的
        ServletContext application2 = this .getServletContext();
        out.print("<br>" + application2);
        // org.apache.catalina.core.ApplicationContextFacade@75f0e503



    }
}
