package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.store.Store;

import java.util.function.Predicate;

public class HRReportEngine implements Report {
    private final Store store;

    private static final String SEP = System.lineSeparator();


    public HRReportEngine(Store store) {
        this.store = store;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("name;salary;")
                .append(SEP);
        for (Employee employee : store.findBy(filter, (x, y) -> (int) (y.getSalary() - x.getSalary()))) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(SEP);
        }
        return text.toString();
    }
}
