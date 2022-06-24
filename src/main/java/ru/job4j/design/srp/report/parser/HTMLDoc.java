package ru.job4j.design.srp.report.parser;

import java.util.Arrays;
import java.util.List;

public class HTMLDoc implements DocumentType {

    private final static String WHITE_SPACE = " ";

    private final static String SEP = "\r\n";

    private final static String HTML_BASE_START = """
            <!DOCTYPE html>
            <html>
                <head>
                    <meta charset="utf-8" />
                    <title>HTML Document</title>
                </head>
                <body>
            """;

    private static final String HTML_BASE_END = """
                </body>
            </html>
            """;
    private static final String HTML_EMPLOYEES_START = """
                    <Employer>
            """;
    private static final String HTML_EMPLOYEES_END = """
                    </Employer>
            """;
    public StringBuilder parse(StringBuilder head) {
        String str = head.toString();
        StringBuilder builder = new StringBuilder(HTML_BASE_START);
        String[] strings = str.split("\r".concat(System.lineSeparator()));
        String[] header = strings[0].split(";");
        List<String> list = Arrays.stream(strings).skip(1).toList();
        for (String element : list) {
            String[] holder = element.split(";");
            builder.append(HTML_EMPLOYEES_START);
            for (int i = 0; i <= header.length - 1; i++) {
                builder.append(WHITE_SPACE.repeat(12))
                        .append(String.format("%s=", header[i]))
                        .append(holder[i]).append("\r").append(System.lineSeparator());
            }
            builder.append(HTML_EMPLOYEES_END);
        }
        builder.append(HTML_BASE_END);
        return builder;
    }
}
