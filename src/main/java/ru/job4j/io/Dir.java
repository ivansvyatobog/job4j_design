package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("File name: %s; File size: %.3fMB\n", file.getName(), (double) file.length() / (1024 * 1024));
        for (File subfile : file.listFiles()) {
            System.out.printf("File name: %s; File size: %.3fMB\n", subfile.getName(), (double) subfile.length() / (1024 * 1024));
        }
    }
}