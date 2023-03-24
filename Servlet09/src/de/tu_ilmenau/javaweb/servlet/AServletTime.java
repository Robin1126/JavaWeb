package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

/**
 * Author : Binbin Luo
 * Date : 24.03.2023
 */
public class AServletTime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date nowTime = new Date();
        request.setAttribute("nowTime",nowTime);
     /*   Object obj = request.getAttribute("nowTime");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("系统时间 = " + obj);*/

        // 使用servlet当中的转发机制，可以在执行了A之后跳转到B
        // servlet不能我们去new，这样不受到tomcat服务器管理，比如无法销毁等等
        // 所以我们要使用转发机制，让B也可以拿到A中设置的request
        // 如果转发？
        // 1. 获取请求转换器对象
        // 里面传的是一个路径,我们不用直接new，转发给B
        RequestDispatcher dispatcher = request.getRequestDispatcher("/b");

        // 2.调用转发器的方法forward,完成转发
        // 将request,response传入到BServlet当中，保证传递的是一致的
//        dispatcher.forward(request,response);
        // 下次访问AServlet的时候就会跳转到B

        // 还可以转发web服务器当中其他有效的资源， 路径不加项目名
        request.getRequestDispatcher("/test.html").forward(request,response);

    }
}
