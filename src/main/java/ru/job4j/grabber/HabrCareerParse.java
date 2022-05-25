package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    public static void main(String[] args) throws IOException {
        Connection connection = Jsoup.connect(PAGE_LINK);
        Document document = connection.get();
        List<Vacancy> vacancyList = new ArrayList<>();
        Elements rows = document.select(".vacancy-card__inner");
        rows.forEach(row -> {
            Element dateElement = row.selectFirst(".vacancy-card__date").child(0);
            String data = dateElement.attr("datetime");
            Element titleElement = row.selectFirst(".vacancy-card__title");
            Element linkElement = titleElement.child(0);
            String vacancyName = titleElement.text();
            vacancyList.add(new Vacancy(vacancyName, String.format("%s%s", SOURCE_LINK, linkElement.attr("href")), data));
        });
        vacancyList.forEach(System.out::println);
    }

    private static class Vacancy {
        private String vacancyName;
        private String vacancyLink;
        private String vacancyDate;

        @Override
        public String toString() {
            return "Vacancy{" +
                    "Название = '" + vacancyName + '\'' +
                    ", Ссылка ='" + vacancyLink + '\'' +
                    ", Дата размещения ='" + vacancyDate + '\'' +
                    '}';
        }

        public Vacancy(String vacancyName, String vacancyLink, String vacancyDate) {
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

        public String getVacancyDate() {
            return vacancyDate;
        }
    }
}