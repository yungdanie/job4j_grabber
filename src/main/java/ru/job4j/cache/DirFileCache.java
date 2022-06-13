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
        Path path = Paths.get(cachingDir.concat("/").concat(key));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файл не существует и не может быть закеширован");
        }
        String res = get(key);
        if (res == null) {
            StringBuilder builder = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new FileReader(path.toFile()))) {
                while (in.ready()) {
                    builder.append(in.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            res = builder.toString();
            put(key, res);
            System.out.println("Файл успешно закеширован");
        }
        return res;
    }

}