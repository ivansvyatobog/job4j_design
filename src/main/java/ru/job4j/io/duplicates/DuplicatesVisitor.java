package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> dublicateFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(), file.getFileName().toString());
        dublicateFiles.putIfAbsent(property, new ArrayList<>());
        dublicateFiles.get(property).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    void printDuplicateFiles() {
        List<Path> rsl = new ArrayList<>();
        dublicateFiles.values().stream()
                .filter(paths -> paths.size() > 1)
                .forEach(rsl::addAll);
        rsl.forEach(System.out::println);
    }
}