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
    private static final String DEFAULT_QUEST = """
            Список комманд:
            0 - Выход
            1 - Загрузить содержимое файла в кеш
            2 - Получить содержимое файла
            """;


    public void changeCacheDir(String dir) {
        Path path = Paths.get(dir);
        try {
            if (!Files.exists(path)) {
                throw new IllegalArgumentException("Директория не существует. Введите корректный путь.");
            } else if (!Files.isDirectory(path)) {
                throw new IllegalArgumentException("Путь не является директорией. Введите корректный путь.");
            } else {
                fileCache = new DirFileCache(dir);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
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
        while (emulator.fileCache == null) {
            emulator.changeCacheDir(ask(DIR_QUEST));
        }
        int ans  = defaultAsk();
        while (ans != EXIT) {
            if (ans == CACHE_FILE) {
                String file = ask(FILE_QUEST);
                try {
                    if (!Files.exists(Path.of(emulator.fileCache.getCachingDir(), file))) {
                        throw new IllegalArgumentException("Файл не существует и не может"
                                .concat("быть закеширован. Введите корректный путь"));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
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
