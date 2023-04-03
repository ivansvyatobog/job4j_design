package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        validate(argsName);
        String output = argsName.get("out");
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path"))); PrintStream out = "stdout".equals(output) ? new PrintStream(System.out) : new PrintStream(new FileOutputStream(output))) {
            scanner.useDelimiter(System.lineSeparator());
            List<String> filters = Arrays.stream(argsName.get("filter").split(",")).toList();
            List<String> elementsList = Arrays.stream(scanner.next().split(argsName.get("delimiter"))).toList();
            List<Integer> elementIndex = filters.stream()
                    .mapToInt(elementsList::indexOf)
                    .filter(i -> i != -1)
                    .boxed()
                    .toList();
            String filteredElements = filter(elementsList, elementIndex, argsName.get("delimiter"));
            out.println(filteredElements);
            while (scanner.hasNext()) {
                List<String> line = Arrays.asList(scanner.next().split(argsName.get("delimiter")));
                String filteredLine = filter(line, elementIndex, argsName.get("delimiter"));
                out.println(filteredLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String filter(List<String> listOfElements, List<Integer> elementIndexes, String delimiter) {
        return elementIndexes.stream()
                .map(listOfElements::get)
                .collect(Collectors.joining(delimiter));
    }

    private static void validate(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("path"))) || !argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("%s - not exist or file format is not correct", argsName.get("path")));
        }
        if (argsName.get("out").isEmpty() || !argsName.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("%s - not exist or file format is not correct", argsName.get("out")));
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException(String.format("%s - filter argument is empty!", argsName.get("filter")));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not enough arguments!");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}