package ru.job4j.design.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.store.Store;

import java.util.function.Predicate;

public class JSONReportEngine implements Report {

    private final Store store;

    public JSONReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder gson = new StringBuilder();
        Gson builder = new GsonBuilder().setPrettyPrinting().setDateFormat("dd:MM:yyyy HH:mm").registerTypeAdapter(Employee.class, new CustomConverter()).create();
        for (Employee emp : store.findBy(filter)) {
            gson.append(builder.toJson(emp));
            gson.append("\n");
        }
        return gson.toString();
    }
}
