package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.report.parser.Configuration;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);

    String generate(Predicate<Employee> filter, Configuration configuration);
}