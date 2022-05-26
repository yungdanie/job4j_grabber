package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private static final String PAGE_NUMBER = "?page=";

    public static void main(String[] args) throws IOException {
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        List<Vacancy> vacancyList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Connection connection = Jsoup.connect(String.format("%s%s%d", PAGE_LINK, PAGE_NUMBER, i));
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element dateElement = row.selectFirst(".vacancy-card__date").child(0);
                String data = dateElement.attr("datetime");
                Element titleElement = row.selectFirst(".vacancy-card__title");
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                vacancyList.add(new Vacancy
                        (vacancyName,
                                String.format("%s%s", SOURCE_LINK, linkElement.attr("href")),
                                parser.parse(data)));
            });
        }
        vacancyList.forEach(System.out::println);
    }

    private static class Vacancy {
        private String vacancyName;
        private String vacancyLink;
        private LocalDateTime vacancyDate;

        @Override
        public String toString() {
            return "Vacancy{" +
                    "Название = '" + vacancyName + '\'' +
                    ", Ссылка ='" + vacancyLink + '\'' +
                    ", Дата размещения ='" + vacancyDate + '\'' +
                    '}';
        }

        public Vacancy(String vacancyName, String vacancyLink, LocalDateTime vacancyDate) {
            this.vacancyName = vacancyName;
            this.vacancyLink = vacancyLink;
            this.vacancyDate = vacancyDate;
        }

        public String getVacancyName() {
            return vacancyName;
        }

        public String getVacancyLink() {
            return vacancyLink;
        }

        public LocalDateTime getVacancyDate() {
            return vacancyDate;
        }
    }
}