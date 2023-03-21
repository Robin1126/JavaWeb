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
public class PostServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // out.println是源代码换行，不是网页换行，网页换行必须要用br

        out.print("        <!doctype html>           ");
        out.print("<html>                             ");
        out.print("	<meta charset = 'utf-8'>       ");
        out.print("	<head>                         ");
        out.print("		<title>post Servlet</title> ");
        out.print("	</head>                        ");
        out.print("	<body>                         ");
        out.print("		<h1>post Servlet</h1>       ");
        out.print("	</body>                        ");
        out.print("</html>                            ");

    }
}
