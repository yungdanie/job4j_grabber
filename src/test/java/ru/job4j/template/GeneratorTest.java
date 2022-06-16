package ru.job4j.template;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

public class GeneratorTest {

    @Test @Ignore
    public void whenNameIsDanielArticleIsYou() {
        Generator generator = new Generate();
        String res = generator.produce("Hi ${name} is ${article}",
                Map.of("name", "Daniel", "article", "you"));
        String exp = "Hi Daniel is you";
        Assert.assertEquals(exp, res);
    }

    @Test @Ignore
    public void whenNumIs1304StatusIsRunning() {
        Generator generator = new Generate();
        String res = generator.produce("machine #${num} status ${status}",
                Map.of("num", "1304", "status", "running"));
        String exp = "machine #1304 status running";
        Assert.assertEquals(exp, res);
    }
}