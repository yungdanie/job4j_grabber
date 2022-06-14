package ru.job4j.cache;

import java.util.Scanner;
import java.util.StringJoiner;

public class Emulator {

    private final DirFileCache fileCache = new DirFileCache();
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String DIR_QUEST = "Напишите путь к кешируемой директории";

    private static final String FILE_QUEST = "Напишите путь к файлу";
    private static final StringJoiner JOINER = new StringJoiner(System.lineSeparator());

    private static final String DEFAULT_QUEST = JOINER.add("Список комманд:").add("0 - Выход")
            .add("1 - Указать кешируемую дерикторию").add("2 - Загрузить содержимое файла в кеш")
            .add("3 - Получить содержимое файла").toString();

    public void changeCacheDir(String dir) {
        fileCache.setCachingDir(dir);
    }

    private static int defaultAsk() {
        System.out.println(DEFAULT_QUEST);
        return SCANNER.nextInt();
    }

    private static String ask(String quest) {
        System.out.println(quest);
        return SCANNER.next();
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.changeCacheDir(ask(DIR_QUEST));
        int ans  = defaultAsk();
        while (ans != 0) {
            if (ans == 1) {
                emulator.changeCacheDir(ask(DIR_QUEST));
            } else if (ans == 2) {
                emulator.fileCache.load(ask(FILE_QUEST));
            } else if (ans == 3) {
                String res = emulator.fileCache.load(ask(FILE_QUEST));
                if (res != null) {
                    System.out.println(res);
                }
            } else {
                System.out.println("Выбранный пункт отсутствует");
            }
            ans = defaultAsk();
        }
    }
}
