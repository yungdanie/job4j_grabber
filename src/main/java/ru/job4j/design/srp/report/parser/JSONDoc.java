package ru.job4j.design.srp.report.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.report.parser.converter.CustomConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class JSONDoc {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public StringBuilder parse(StringBuilder str) {
        String string = str.toString();
        StringBuilder builder;
        try {
            builder = toGson(Arrays.stream(string.split(System.lineSeparator())).toList());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return builder;
    }

    private List<Employee> createNewEmployers(List<String> employers) throws ParseException {
        List<String> header = Arrays.stream(employers.get(0).split(";")).toList();
        List<String> sepList = employers.subList(1, employers.size());
        List<Employee> employeeList = new ArrayList<>();
        for (String emp : sepList) {
            Employee employee = new Employee();
            String[] empSep = emp.split(";");
            int i = 0;
            if (header.contains("name")) {
                employee.setName(empSep[i]);
                ++i;
            }
            if (header.contains("fired")) {
                Calendar a = Calendar.getInstance();
                a.setTime(DATE_FORMAT.parse(empSep[i]));
                employee.setHired(a);
                ++i;
            }
            if (header.contains("hired")) {
                Calendar a = Calendar.getInstance();
                a.setTime(DATE_FORMAT.parse(empSep[i]));
                employee.setFired(a);
                ++i;
            }
            if (header.contains("salary")) {
                employee.setSalary(Double.parseDouble(empSep[i]));
            }
            employeeList.add(employee);
        }
        return employeeList;
    }

    private StringBuilder toGson(List<String> employers) throws ParseException {
        StringBuilder gson = new StringBuilder();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CustomConverter.class, new CustomConverter());
        Gson defaultGson = gsonBuilder.create();
        List<Employee> list = createNewEmployers(employers);
        for (Employee emp : list) {
            gson.append(System.lineSeparator());
            gson.append(defaultGson.toJson(emp));
        }
        return gson;
    }
}
