package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines()
                    .filter(s -> !s.isEmpty() && !s.startsWith("#"))
                    .filter(el -> {
                        if (!el.contains("=")) {
                            throw new IllegalArgumentException(
                                    "the string does not contain the equal sign");
                        }
                        return true;
                    })
                    .map(el -> el.split("=", 2))
                    .filter(el -> {
                        if (el[0].isEmpty() || el[1].isEmpty()) {
                            throw new IllegalArgumentException(
                                    "there is no value before or after the equal sign"
                            );
                        }
                        return true;
                    })
                    .collect(Collectors.toMap(el -> el[0], e -> e[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}