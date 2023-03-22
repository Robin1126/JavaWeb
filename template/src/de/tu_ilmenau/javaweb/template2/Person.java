package de.tu_ilmenau.javaweb.template2;

/**
 * Author : Binbin Luo
 * Date : 22.03.2023
 */
// 模板方法设计模式，可以写一个模板类，方法可以让子类去继承，核心步骤可以用final来施加保护，定义核心算法骨架，具体的实现步骤可以交给子类去完成
public abstract class Person {
    public final void day() { // 不能被覆盖，核心的算法也可以得到保护，核心的方法保护，具体的算法交给子类实现
        // 核心算法中的步骤都是定好的，不能被改变了
        getup();
        wash();
        breakfast();
        doSome();
        dinner();
        sleep();
    }
    // 其中的某些步骤不因子类的变化而变化，这样就可以通过继承实现子类的复用
    public void getup() {
        System.out.println("getup");
    }

    public void wash() {
        System.out.println("wash");
    }

    public void breakfast() {
        System.out.println("breakfast");
    }

    public abstract void doSome();
    public void dinner() {
        System.out.println("dinner");
    }

    public void sleep() {
        System.out.println("sleep");
    }
}
