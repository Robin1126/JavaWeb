package Listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

/**
 * Author : Binbin Luo
 * Date : 06.04.2023
 */
@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("Session中存储了数据");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("Session中数据被删除");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("Session中数据被修改");

    }
}
