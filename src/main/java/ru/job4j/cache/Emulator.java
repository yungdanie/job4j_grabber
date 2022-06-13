package ru.job4j.cache;

import java.util.Scanner;
import java.util.StringJoiner;

public class Emulator {

    private final DirFileCache fileCache = new DirFileCache();
    private static final Scanner SCANNER = new Scanner(System.in);

    public void changeCacheDir(String dir) {
        fileCache.setCachingDir(dir);
    }

    private static int defaultAsk() {
        String sep = System.lineSeparator();
        StringJoiner joiner = new StringJoiner(sep);
        joiner.add("Список комманд:").add("0 - Выход")
                .add("1 - Указать кешируемую дерикторию")
                .add("2 - Загрузить содержимое файла в кеш")
                .add("3 - Получить содержимое файла");
        System.out.println(joiner);
        return SCANNER.nextInt();
    }

    private static String ask(String quest) {
        System.out.println(quest);
        return SCANNER.next();
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.changeCacheDir(ask("Напишите путь к кешируемой директории"));
        int ans  = defaultAsk();
        while (ans != 0) {
            if (ans == 1) {
                emulator.changeCacheDir(ask("Напишите путь к директории"));
            } else if (ans == 2) {
                emulator.fileCache.load(ask("Напишите путь к файлу"));
            } else if (ans == 3) {
                String res = emulator.fileCache.load(ask("Напишите путь к файлу"));
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