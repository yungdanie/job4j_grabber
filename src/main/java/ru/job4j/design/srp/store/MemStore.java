package ru.job4j.design.srp.store;

import ru.job4j.design.srp.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {

    private final List<Employee> employees = new ArrayList<>();


    public void add(Employee em) {
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter, Comparator<Employee> comparator) {
        return employees.stream().filter(filter).sorted(comparator).collect(Collectors.toList());
    }
}