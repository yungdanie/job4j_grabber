package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class HabrCareerParse implements Parse {

    private static HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private static final String PAGE_NUMBER = "?page=";

    public static void main(String[] args) {
        HabrCareerParse parser = new HabrCareerParse();
        List<Post> list = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            list = parser.list(String.format("%s%s%d", PAGE_LINK, PAGE_NUMBER, i));
        }
        System.out.println(list);
    }

    private String retrieveDescription(String link) {
        Connection connection = Jsoup.connect(link);
        Document document;
        try {
            document = connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element element = document.selectFirst(".style-ugc");
        return element.text();
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        String sep = System.lineSeparator();
        Connection connection = Jsoup.connect(String.format(link));
        try {
            Document page = connection.get();
            Elements elements = page.select(".vacancy-card__title");
            for (Element element : elements) {
                Document document = Jsoup.connect(String.format(
                        "%s%s",
                        SOURCE_LINK,
                        element.child(0).attr("href"))).get();
                LocalDateTime date = parser.parse(
                        document.selectFirst(".job_show_header__date").
                                child(0).
                                attr("datetime"));
                StringBuilder title = new StringBuilder();
                title.append("Компания: ").append(document.
                                select(".company_name").
                                get(0).
                                text()).
                        append(sep);
                title.append("Title: ").
                        append(document.
                                selectFirst(".page-title").
                                child(0).
                                text());
                StringBuilder desc = new StringBuilder();
                for (Element descr : document.select(".style-ugc").select("p, ul")) {
                    if (descr.children().isEmpty()
                            && !descr.text().isEmpty()
                            || descr.children().is("br")) {
                        desc.append(sep).append(descr.text())
                                .append(sep.repeat(3));
                    } else if (!descr.children().isEmpty()) {
                        if (descr.children().is("strong")
                                && !descr.children().select("strong").text().isEmpty()) {
                            desc.append(sep).append(descr.text()).
                                    append(sep);
                        } else if (descr.children().is("li")) {
                            for (Element li : descr.children()) {
                                desc.append("#".repeat(3)).
                                        append("-").
                                        append(li.text()).append(sep);
                            }
                        }
                    }
                }
                list.add(new Post(title.toString(), link, desc.toString(), date));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}