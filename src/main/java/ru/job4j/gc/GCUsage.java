package ru.job4j.gc;

import java.lang.instrument.Instrumentation;

public class GCUsage {

    private static final Runtime ENVIRONMENT = Runtime.getRuntime();
    private static final long KB = 1000;

    private static final long MB = KB * KB;

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory);
        System.out.printf("Total: %d%n", totalMemory);
        System.out.printf("Max: %d%n", maxMemory);
    }

    public static void main(String[] args) throws Throwable {
        info();
        long usedMemoryBefore = ENVIRONMENT.totalMemory() - ENVIRONMENT.freeMemory();
        System.out.println(usedMemoryBefore);
        for (int i = 0; i < 10000; i++) {
            new User("А", "Б", i);
            System.out.println(ENVIRONMENT.totalMemory() - ENVIRONMENT.freeMemory() - usedMemoryBefore);
            Thread.sleep(1500);
        }
        info();
    }
}
