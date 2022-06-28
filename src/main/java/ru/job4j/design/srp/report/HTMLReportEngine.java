package ru.job4j.design.srp.report;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.store.Store;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class HTMLReportEngine implements Report {
    private final Store store;
    private final static String WHITE_SPACE = " ".repeat(12);

    private final static String SEP = "\n";

    private final static String HTML_BASE_START = """
            <!DOCTYPE html>
            <html>
                <head>
                    <meta charset="utf-8" />
                    <title>HTML Document</title>
                </head>
                <body>
            """;

    private static final String HTML_BASE_END = """
                </body>
            </html>
            """;
    private static final String HTML_EMPLOYEES_START = """
                    <Employer>
            """;
    private static final String HTML_EMPLOYEES_END = """
                    </Employer>
            """;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public HTMLReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(HTML_BASE_START);
        for (Employee employee : store.findBy(filter)) {
            text.append(HTML_EMPLOYEES_START)
                    .append(WHITE_SPACE).append("name=".
                            concat(employee.getName())).append(SEP)
                    .append(WHITE_SPACE).append("hired=").append(DATE_FORMAT.format(employee.getHired().getTime()))
                    .append(SEP)
                    .append(WHITE_SPACE).append("fired=").append(DATE_FORMAT.format(employee.getFired().getTime()))
                    .append(SEP)
                    .append(WHITE_SPACE).append("salary="
                            .concat(String.valueOf(employee.getSalary()))).append(SEP)
                    .append(HTML_EMPLOYEES_END);
        }
        text.append(HTML_BASE_END);
        return text.toString();
    }
}
