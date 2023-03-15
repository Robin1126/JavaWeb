package de.tu_ilmenau.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet implements Servlet {
	// 5 Methodes
	public void init(ServletConfig config)  throws ServletException {
		
	
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// 设置是相应的内容类型是普通文本或者html
		// 这样就能将字符串中的html代码识别并且输出
		response.setContentType("text/html");
		// 这句话的设置不要放到获取流之后，需要在获取流对象之前

		// 如何输出信息到浏览器上面：使用ServletRespose接口：response
		System.out.println("My first Servlet, Hello Servlet!");
		PrintWriter out = response.getWriter(); //获得一个输出流，负责输出字符串到浏览器
		out.print("Hello, Servlet, You are my first servlet!");

		out.print("<h1>Hello servlet!</h1>"); // 直接输出一段html代码

		/* 不需要flush和close tomcat可以自动帮我们完成！*/
	
	}

	public void destroy() {
		
	}

	public String getServletInfo() {
		return "";
	}

	public ServletConfig getServletConfig() {
		return null;
	}

}