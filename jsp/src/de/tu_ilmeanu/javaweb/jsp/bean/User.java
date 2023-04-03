package de.tu_ilmeanu.javaweb.jsp.bean;

/**
 * Author : Binbin Luo
 * Date : 03.04.2023
 * 符合javabean规范的一个java类
 */
public class User {
    private String name;
    private String password;
    private int age;

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    private Address addr;

    public User() {
    }

    public User(String name, String password, int age, Address addr) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return "luobinbin@gmail.com";
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", addr=" + addr +
                '}';
    }
}
