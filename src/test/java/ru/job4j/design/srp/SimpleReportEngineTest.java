package ru.job4j.design.srp;

import org.junit.Test;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.model.ExchangeRate;
import ru.job4j.design.srp.report.Report;
import ru.job4j.design.srp.report.SimpleReportEngine;
import ru.job4j.design.srp.report.parser.Configuration;
import ru.job4j.design.srp.report.parser.DocumentType;
import ru.job4j.design.srp.report.parser.HTMLDoc;
import ru.job4j.design.srp.report.parser.Parser;
import ru.job4j.design.srp.store.MemStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleReportEngineTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new SimpleReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLDocumentGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        String time = DATE_FORMAT.format(now.getTime());
        DocumentType type = new HTMLDoc();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Jenya", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        Configuration configuration = Parser.getDefaultConfiguration().setDocumentType(type);
        Report engine = new SimpleReportEngine(store);
        String expect = String.format("""
                <!DOCTYPE html>
                <html>
                    <head>
                        <meta charset="utf-8" />
                        <title>HTML Document</title>
                    </head>
                    <body>
                        <Employer>
                            name=Ivan\r
                            hired=%s\r
                            fired=%s\r
                            salary=100.0\r
                        </Employer>
                        <Employer>
                            name=Jenya\r
                            hired=%s\r
                            fired=%s\r
                            salary=300.0\r
                        </Employer>
                    </body>
                </html>
                    """, time, time, time, time);
        assertThat(engine.generate(em -> true, configuration), is(expect));
    }

    @Test
    public void whenHRDocumentGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Jenya", now, now, 300);
        Employee worker3 = new Employee("Vanya", now, now, 50);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Configuration configuration = Parser.getDefaultConfiguration().setHeader("name;salary;")
                .setComparator((o1, o2) -> (int) (o2.getSalary() - o1.getSalary()));
        Report engine = new SimpleReportEngine(store);
        String expect = """
                name;salary;\r
                Jenya;300.0;\r
                Ivan;100.0;\r
                Vanya;50.0;\r
                """;
        assertThat(engine.generate(em -> true, configuration), is(expect));
    }

    @Test
    public void whenAccountantDocumentGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        String time = DATE_FORMAT.format(now.getTime());
        Employee worker1 = new Employee("Ivan", now, now, 110);
        Employee worker2 = new Employee("Jenya", now, now, 165);
        store.add(worker1);
        store.add(worker2);
        Configuration configuration = Parser.getDefaultConfiguration().setValueCurrency(ExchangeRate.DOLLAR);
        Report engine = new SimpleReportEngine(store);
        String expect = String.format("""
                    name;hired;fired;salary;\r
                    Ivan;%1$s;%1$s;2.0;\r
                    Jenya;%1$s;%1$s;3.0;\r
                    """, time);
        assertThat(engine.generate(em -> true, configuration), is(expect));
    }
}