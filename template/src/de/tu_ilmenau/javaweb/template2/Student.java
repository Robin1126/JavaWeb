package de.tu_ilmenau.javaweb.template2;

/**
 * Author : Binbin Luo
 * Date : 22.03.2023
 */

// 将重复的代码放到一个类当中，将要不同实现的代码写成抽象方法，在子类中实现，公用的代码就不用写了
public class Student extends Person{
    @Override
    public void doSome() {
        System.out.println("study");
    }
}
