package com.example.acespritech_umesh.umeshproject.Data;

/**
 * Created by acespritech-umesh on 14/7/17.
 */

public class Employee {
    int id;

    public Employee(int id, String name, String department, String salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    String name, department;
    String salary;


    public Employee(int delete_id) {
        this.id = delete_id;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
