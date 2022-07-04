package ru.job4j.design.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.model.Employees;
import ru.job4j.design.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReportEngine implements Report {
    private final Store store;
    private final Marshaller marshaller;

    public XMLReportEngine(Store store) {
        this.store = store;
        try {
            marshaller = JAXBContext.newInstance(Employees.class).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder builder = new StringBuilder();
        try {
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(store.findBy(filter)), writer);
                builder.append(writer.getBuffer().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
