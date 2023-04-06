package Listeners;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Author : Binbin Luo
 * Date : 06.04.2023
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // request对象销毁的时候执行
        System.out.println("request对象销毁了");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // request对象创建的时候执行
        System.out.println("request对象初始化了");
    }
}
