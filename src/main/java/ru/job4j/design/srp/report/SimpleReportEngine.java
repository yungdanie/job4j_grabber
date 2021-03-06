package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.store.Store;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class SimpleReportEngine implements Report {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private final Store store;


    public SimpleReportEngine(Store store) {
        this.store = store;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

}
