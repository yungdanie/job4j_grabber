package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private String cachingDir;

    public DirFileCache() {
    }

    public void setCachingDir(String cachingDir) {
        Path path = Paths.get(cachingDir);
        if (Files.exists(path) && Files.isDirectory(path)) {
            this.cachingDir = cachingDir;
            System.out.println("Директория успешно изменена");
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected String load(String key) {
        Path path = Path.of(cachingDir, key);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файл не существует и не может быть закеширован");
        }
        String res = get(key);
        if (res == null) {
            try {
                res = Files.readString(path);
                put(key, res);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Файл успешно закеширован");
        }
        return res;
    }

}