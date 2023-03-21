package de.tu_ilmenau.javaweb.Servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 21.03.2023
 */
public class GetServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); // 这里的text和html不能写反，不然会出现下载样式
        PrintWriter out = response.getWriter();

        out.print("        <!doctype html>           ");
        out.print("<html>                             ");
        out.print("	<meta charset = 'utf-8'>       ");
        out.print("	<head>                         ");
        out.print("		<title>get Servlet</title> ");
        out.print("	</head>                        ");
        out.print("	<body>                         ");
        out.print("		<h1>get Servlet</h1>       ");
        out.print("	</body>                        ");
        out.print("</html>                            ");
    }
}
