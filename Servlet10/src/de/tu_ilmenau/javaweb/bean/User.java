package de.tu_ilmenau.javaweb.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Author : Binbin Luo
 * Date : 29.03.2023
 */
/*
    1. 一个普通的javabean
    2. 什么是javabean？
    java是咖啡
    bean是豆子
    javabean就是咖啡豆
    3. 一个javabean是有要求的
        比如私有化属性
        对外提供方法等...
 */
public class User implements Serializable {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
