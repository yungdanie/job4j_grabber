package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.model.ExchangeRate;
import ru.job4j.design.srp.store.Store;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class AccountantReportEngine implements Report {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private final Store store;

    private final double rate = ExchangeRate.DOLLAR.getRate();

    private static final String SEP = "\n";

    public AccountantReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("name;hired;fired;salary;").append(SEP);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary() / rate).append(";")
                    .append(SEP);
        }
        return text.toString();
    }
}
