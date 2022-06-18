package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringJoiner;

public class Emulator {

    private DirFileCache fileCache = null;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String DIR_QUEST = "Напишите путь к кешируемой директории";

    private static final String FILE_QUEST = "Напишите путь к файлу";
    private static final StringJoiner JOINER = new StringJoiner(System.lineSeparator());
    private static final int CACHE_FILE = 1;
    private static final int CACHE_AND_GET_FILE = 2;
    private static final int EXIT = 0;

    private static final String DEFAULT_QUEST = JOINER.add("Список комманд:").add("0 - Выход")
            .add("1 - Указать кешируемую дерикторию").add("2 - Загрузить содержимое файла в кеш")
            .add("3 - Получить содержимое файла").toString();


    public void changeCacheDir(String dir) {
        Path path = Paths.get(dir);
        if (Files.exists(path) && Files.isDirectory(path)) {
            fileCache = new DirFileCache(dir);
        }
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
        while (ans != EXIT) {
            if (ans == CACHE_FILE) {
                String file = ask(FILE_QUEST);
                if (!Files.exists(Path.of(emulator.fileCache.getCachingDir(), file))) {
                    throw new IllegalArgumentException("Файл не существует и не может быть закеширован");
                }
                emulator.fileCache.get(file);
            } else if (ans == CACHE_AND_GET_FILE) {
                String res = emulator.fileCache.get(ask(FILE_QUEST));
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
