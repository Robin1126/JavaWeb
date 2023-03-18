import jakarta.servlet.*;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 18.03.2023
 */
public abstract class GenericServlet implements Servlet {
    private ServletConfig config;
    @Override
    public final void init(ServletConfig config) throws ServletException {
        this.config = config;
        init();
    }

    public void init() {}


    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException ;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
