package ru.job4j.design.srp.report.parser;

import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.model.ExchangeRate;

import java.util.Comparator;

public interface Configuration {

    Configuration setComparator(Comparator<Employee> comparator);

    Configuration setValueCurrency(ExchangeRate rate);

    Configuration setDocumentType(DocumentType type);
    Configuration setHeader(String header);

    Comparator<Employee> getComparator();

    ExchangeRate getValueCurrency();

    DocumentType getDocumentType();

    String getHeader();
}
