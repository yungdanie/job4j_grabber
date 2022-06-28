package ru.job4j.design.srp.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlType
@XmlRootElement(name = "List")
public class Employees {
    private List<Employee> employees;

    public Employees() {
    }

    public Employees(List<Employee> list) {
        employees = list;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
