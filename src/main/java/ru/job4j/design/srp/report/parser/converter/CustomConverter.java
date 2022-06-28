package ru.job4j.design.srp.report.parser.converter;

import com.google.gson.*;
import ru.job4j.design.srp.model.Employee;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomConverter implements JsonSerializer<Employee> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public JsonElement serialize(Employee employee, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        String name = employee.getName();
        Calendar hired = employee.getHired();
        Calendar fired = employee.getFired();
        double salary = employee.getSalary();
        if (name != null) {
            object.addProperty("name", name);
        }
        if (hired != null) {
            object.addProperty("hired", DATE_FORMAT.format(hired));
        }
        if (fired != null) {
            object.addProperty("fired", DATE_FORMAT.format(fired));
        }
        if (salary != 0.0) {
            object.addProperty("salary", salary);
        }
        return object;
    }
}
