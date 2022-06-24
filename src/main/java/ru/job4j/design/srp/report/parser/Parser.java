package ru.job4j.design.srp.report.parser;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.model.ExchangeRate;

import java.util.Comparator;

public class Parser implements Configuration {

    private ExchangeRate rate;
    private Comparator<Employee> comparator;
    private DocumentType document;

    private String header;

    private Parser() {
    }

    @Override
    public Comparator<Employee> getComparator() {
        return comparator;
    }

    @Override
    public ExchangeRate getValueCurrency() {
        return rate;
    }

    @Override
    public DocumentType getDocumentType() {
        return document;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public Configuration setValueCurrency(ExchangeRate rate) {
        this.rate = rate;
        return this;
    }

    @Override
    public Configuration setDocumentType(DocumentType type) {
        this.document = type;
        return this;
    }

    @Override
    public Configuration setHeader(String header) {
        String[] splited = header.split(";");
        try {
            if (splited.length > 4 || splited.length == 0) {
                throw new IllegalArgumentException("Количество элементов в загаловке 5.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Заголовок установлен не был.");
            e.printStackTrace();
        }
        this.header = header;
        return this;
    }

    @Override
    public Configuration setComparator(Comparator<Employee> comparator) {
        this.comparator = comparator;
        return this;
    }

    public static Configuration getDefaultConfiguration() {
        return new Parser();
    }
}
