package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    final private String cachingDir;

    public DirFileCache(String cache) {
        this.cachingDir = cache;
    }

    public String getCachingDir() {
        return cachingDir;
    }
    @Override
    protected String load(String key) {
        Path path = Path.of(cachingDir, key);
        String res = null;
        try {
            res = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}