package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.model.ExchangeRate;
import ru.job4j.design.srp.report.parser.Configuration;
import ru.job4j.design.srp.report.parser.DocumentType;
import ru.job4j.design.srp.store.Store;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class SimpleReportEngine implements Report {

    private static final String DEFAULT_HEADER = "name;hired;fired;salary;";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private final Store store;

    private final String sep = System.lineSeparator();

    private Boolean nameCond = false;

    private boolean hiredCond = false;

    private boolean firedCond = false;

    private boolean salaryCond = false;


    public SimpleReportEngine(Store store) {
        this.store = store;
    }

    private void setDefaultValues() {
        nameCond = false;
        hiredCond = false;
        firedCond = false;
        salaryCond = false;
    }

    private void setDefaultValuesToHigh() {
        nameCond = true;
        hiredCond = true;
        firedCond = true;
        salaryCond = true;
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

    @SuppressWarnings("checkstyle:InnerAssignment")
    @Override
    public String generate(Predicate<Employee> filter, Configuration configuration) {
        StringBuilder text = new StringBuilder();
        Comparator<Employee> comparator = configuration.getComparator();
        ExchangeRate rate = configuration.getValueCurrency();
        DocumentType type = configuration.getDocumentType();
        String headline = configuration.getHeader();
        if (headline != null) {
            List<String> header = Arrays.stream(headline.split(";"))
                    .map(x -> {
                        x = x.toLowerCase(Locale.ROOT);
                        try {
                            switch (x) {
                                case "name" -> {
                                    nameCond = true;
                                }
                                case "hired" -> {
                                    hiredCond = true;
                                }
                                case "fired" -> {
                                    firedCond = true;
                                }
                                case "salary" -> {
                                    salaryCond = true;
                                }
                                default -> throw new IllegalArgumentException("Неизвестное значение " + x + ".".concat(
                                        "Значение будет пропущено."
                                ));
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        return x;
                    })
                    .toList();
            for (String str : header) {
                text.append(str).append(";");
            }
        } else {
            text.append(DEFAULT_HEADER);
            setDefaultValuesToHigh();
        }
        text.append(sep);
        List<Employee> list;
        if (comparator != null) {
            list = store.findBy(filter, comparator);
        } else {
            list = store.findBy(filter);
        }
        for (Employee employee : list) {
            if (nameCond) {
                text.append(employee.getName()).append(";");
            }
            if (hiredCond) {
                text.append(DATE_FORMAT.format(employee.getHired().getTime())).append(";");
            }
            if (firedCond) {
                text.append(DATE_FORMAT.format(employee.getFired().getTime())).append(";");
            }
            if (salaryCond) {
                if (rate != null) {
                    text.append(employee.getSalary() / rate.getRate()).append(";");
                } else {
                    text.append(employee.getSalary()).append(";");
                }
            }
            text.append(sep);
        }
        if (type != null) {
            text = type.parse(text);
        }
        setDefaultValues();
        return text.toString();
    }
}
