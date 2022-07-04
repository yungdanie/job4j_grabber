package ru.job4j.design.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.model.Employee;
import ru.job4j.design.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class JSONReportEngine implements Report {

    private final Store store;
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public JSONReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder gson = new StringBuilder();
        List<Employee> list = store.findBy(filter);
        gson.append(GSON.toJson(list));
        return gson.toString();
    }
}
