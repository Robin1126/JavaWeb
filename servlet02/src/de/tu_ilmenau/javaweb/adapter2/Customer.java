package de.tu_ilmenau.javaweb.adapter2;

/**
 * Author : Binbin Luo
 * Date : 18.03.2023
 */
public class Customer extends CustomerAdapter{
    // 直接去继承CustomerAdapter 就不用实现MyInterface的所有接口了
    // 总结就是 使用一个抽象类去实现接口，然后将需要使用的方法定义成为抽象方法，需要的时候新建类去实现即可
    @Override
    public void m2() {

    }
}
