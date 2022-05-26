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

public class HabrCareerParse {

    private static HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
    private static int id;
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private static final String PAGE_NUMBER = "?page=";

    public static void main(String[] args) throws IOException {
        Set<Post> list = new HashSet<>();
        for (int i = 1; i <= 1; i++) {
            Connection connection = Jsoup.connect(String.format("%s%s%d", PAGE_LINK, PAGE_NUMBER, i));
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__title");
            rows.forEach(row -> {
                list.add(createPost(String.format(
                        "%s%s",
                        SOURCE_LINK,
                        row.child(0).attr("href"))));
            });
        }
        list.forEach(System.out::println);
    }

    private static Post createPost(String link) {
        try {
            Document document = Jsoup.connect(link).get();
            String sep = System.lineSeparator();
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
            for (Element element : document.select(".style-ugc").select("p, ul")) {
                if (element.children().isEmpty() || element.children().is("br")) {
                    desc.append(sep).append(element.text())
                            .append(sep.repeat(3));
                } else if (!element.children().isEmpty()) {
                    if (element.children().is("strong") &&
                            !element.children().select("strong").text().isEmpty()) {
                        desc.append(sep).append(element.text()).
                                append(sep);
                    } else if (element.children().is("li")) {
                        for (Element li : element.children()) {
                            desc.append("#".repeat(3)).
                                    append("-").
                                    append(li.text()).append(sep);
                        }
                    }
                }
            }

        return new Post(id++, title.toString(), link, desc.toString(), date);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}