import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 18.03.2023
 */
public class LoginServlet extends GenericServlet{
    // 将ServletConfig改成成员变量，这样后面的service就可以使用

    // 如果我们要重写init方法，但是不想破坏原来的init，那我们可以在GernericServlet中加入一个init方法，之后我们重写init方法
    // 重写的是没有参数的init方法
    // 不要重写默认的init方法，不然有可能会程序崩溃的

    public void init() {
        System.out.println("LoginServlet's methode executed! ");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletConfig config = this.getServletConfig();
        System.out.println("config的内容是：" + config );
         // org.apache.catalina.core.StandardWrapperFacade@72c778d4
    }
}
