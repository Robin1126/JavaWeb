package de.tu_ilmenau.javaweb.template1;

/**
 * Author : Binbin Luo
 * Date : 22.03.2023
 */
/*
    问题： 1 算法重复
          2. 代码没有得到复用
 */
public class Teacher {
    public void day() {
        getup();
        wash();
        breakfast();
        doSome();
        dinner();
        sleep();
    }
    public void getup() {
        System.out.println("getup");
    }

    public void wash() {
        System.out.println("wash");
    }

    public void breakfast() {
        System.out.println("breakfast");
    }

    public void doSome() {
        System.out.println("teach");
    }

    public void dinner() {
        System.out.println("dinner");
    }

    public void sleep() {
        System.out.println("sleep");
    }
}
