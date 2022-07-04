package ru.job4j.design.srp;

import org.junit.Test;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.report.*;
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
        assertThat(expect.toString(), is(engine.generate(em -> true)));
    }

    @Test
    public void whenHTMLDocumentGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        String time = DATE_FORMAT.format(now.getTime());
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Jenya", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HTMLReportEngine(store);
        String expect = String.format("""
                <!DOCTYPE html>
                <html>
                    <head>
                        <meta charset="utf-8" />
                        <title>HTML Document</title>
                    </head>
                    <body>
                        <Employer>
                            name=Ivan
                            hired=%s
                            fired=%s
                            salary=100.0
                        </Employer>
                        <Employer>
                            name=Jenya
                            hired=%s
                            fired=%s
                            salary=300.0
                        </Employer>
                    </body>
                </html>
                """, time, time, time, time);
        assertThat(expect, is(engine.generate(em -> true)));
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

        Report engine = new HRReportEngine(store);
        String expect = """
                name;salary;\r
                Jenya;300.0;\r
                Ivan;100.0;\r
                Vanya;50.0;\r
                """;
        assertThat(expect, is(engine.generate(em -> true)));
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
        Report engine = new AccountantReportEngine(store);
        String expect = String.format("""
                    name;hired;fired;salary;\r
                    Ivan;%1$s;%1$s;2.0;\r
                    Jenya;%1$s;%1$s;3.0;\r
                    """, time);
        assertThat(expect, is(engine.generate(em -> true)));
    }


    @Test
    public void whenJsonDocumentGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        now.set(2022, Calendar.AUGUST, 4, 16, 4, 12);
        String time = DATE_FORMAT.format(now.getTime());
        Employee worker1 = new Employee("Ivan", now, now, 110);
        Employee worker2 = new Employee("Jenya", now, now, 165);
        store.add(worker1);
        store.add(worker2);
        Report engine = new JSONReportEngine(store);
        String expect = """
                [
                  {
                    "name": "Ivan",
                    "hired": {
                      "year": 2022,
                      "month": 7,
                      "dayOfMonth": 4,
                      "hourOfDay": 16,
                      "minute": 4,
                      "second": 12
                    },
                    "fired": {
                      "year": 2022,
                      "month": 7,
                      "dayOfMonth": 4,
                      "hourOfDay": 16,
                      "minute": 4,
                      "second": 12
                    },
                    "salary": 110.0
                  },
                  {
                    "name": "Jenya",
                    "hired": {
                      "year": 2022,
                      "month": 7,
                      "dayOfMonth": 4,
                      "hourOfDay": 16,
                      "minute": 4,
                      "second": 12
                    },
                    "fired": {
                      "year": 2022,
                      "month": 7,
                      "dayOfMonth": 4,
                      "hourOfDay": 16,
                      "minute": 4,
                      "second": 12
                    },
                    "salary": 165.0
                  }
                ]""";
        assertThat(expect, is(engine.generate(em -> true)));
    }

    @Test
    public void whenXMLDocumentGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        String time = DATE_FORMAT.format(now.getTime());
        Employee worker1 = new Employee("Ivan", now, now, 110);
        Employee worker2 = new Employee("Jenya", now, now, 165);
        store.add(worker1);
        store.add(worker2);
        Report engine = new XMLReportEngine(store);
        String expect = String.format("""
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <List>
                    <employees>
                        <name>Ivan</name>
                        <hired>%1$s</hired>
                        <fired>%1$s</fired>
                        <salary>110.0</salary>
                    </employees>
                    <employees>
                        <name>Jenya</name>
                        <hired>%1$s</hired>
                        <fired>%1$s</fired>
                        <salary>165.0</salary>
                    </employees>
                </List>
                """, time);
        assertThat(expect, is(engine.generate(em -> true)));
    }
}