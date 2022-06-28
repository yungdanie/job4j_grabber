package ru.job4j.design.srp.model;


import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@XmlType(propOrder = {"name", "hired", "fired", "salary"})
@XmlRootElement(name = "Employer")
public class Employee {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlJavaTypeAdapter(DateFormatterAdapter.class)
    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    @XmlJavaTypeAdapter(DateFormatterAdapter.class)
    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private static class DateFormatterAdapter extends XmlAdapter<String, Calendar> {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");


        @Override
        public Calendar unmarshal(String s) throws Exception {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(s));
            return calendar;
        }

        @Override
        public String marshal(Calendar calendar) throws Exception {
            return dateFormat.format(calendar.getTime());
        }
    }

}