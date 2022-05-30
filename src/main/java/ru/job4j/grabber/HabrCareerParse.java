package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class HabrCareerParse implements Parse {

    private static DateTimeParser parser = new HabrCareerDateTimeParser();
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private static final String PAGE_NUMBER = "?page=";

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.parser = dateTimeParser;
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
        for (int i = 1; i <= 5; i++) {
            Connection connection = Jsoup.connect(String.format("%s%s%d", PAGE_LINK, PAGE_NUMBER, i));
            try {
                Document page = connection.get();
                Elements elements = page.select(".vacancy-card__title");
                for (Element element : elements) {
                    list.add(parsing(element));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    private Post parsing(Element element) {
        String sep = System.lineSeparator();
        Post post = null;
        String pageLink = String.format("%s%s", SOURCE_LINK, element.child(0).attr("href"));
        try {
            Document document = Jsoup.connect(pageLink).get();
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
            String desc = retrieveDescription(pageLink);
            post = new Post(title.toString(), pageLink, desc, date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }
}