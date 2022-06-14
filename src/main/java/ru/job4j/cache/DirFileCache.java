package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    public String getCachingDir() {
        return cachingDir;
    }

    private String cachingDir;

    public DirFileCache(String cache) {
        this.cachingDir = cache;
    }

    public void setCachingDir(String cachingDir) {
        this.cachingDir = cachingDir;
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