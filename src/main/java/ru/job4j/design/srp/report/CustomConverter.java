package ru.job4j.design.srp.report;

import com.google.gson.*;
import ru.job4j.design.srp.model.Employee;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class CustomConverter implements JsonSerializer<Employee>, JsonDeserializer<Employee> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public JsonElement serialize(Employee employee, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("name", employee.getName());
        object.addProperty("hired", DATE_FORMAT.format(employee.getHired().getTime()));
        object.addProperty("fired", DATE_FORMAT.format(employee.getFired().getTime()));
        object.addProperty("salary", employee.getSalary());
        return object;
    }

    @Override
    public Employee deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }
}
