package ru.javawebinar.topjava.model;

public class Employee {

    private long id;

    private double salary;

    public Employee() {
    }

    public Employee(long parseLong, double parseDouble) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", salary=" + salary +
                '}';
    }
}
