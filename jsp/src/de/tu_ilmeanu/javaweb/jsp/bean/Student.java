package de.tu_ilmeanu.javaweb.jsp.bean;

import java.util.Objects;

/**
 * Author : Binbin Luo
 * Date : 04.04.2023
 */
public class Student {
    private String name;
    private String id;

    @Override
    public boolean equals(Object o) {
        System.out.println("equals方法执行了！");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Student() {
    }
}
