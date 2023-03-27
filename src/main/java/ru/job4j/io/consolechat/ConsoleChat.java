package ru.job4j.io.consolechat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String pathLog;
    private final String botAnswers;
    private boolean flag = true;


    public ConsoleChat(String pathLog, String botAnswers) {
        this.pathLog = pathLog;
        this.botAnswers = botAnswers;
    }

    public void run() {
        String command = null;
        Scanner scanner = new Scanner(System.in);
        List<String> botPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        while (!OUT.equals(command)) {
            command = scanner.nextLine();
            if (STOP.equals(command)) {
                flag = false;
            } else if (CONTINUE.equals(command)) {
                flag = true;
            }
            log.add(command);
            if (flag) {
                String randomPhrase = botPhrases.get(new Random().nextInt(botPhrases.size()));
                System.out.println(randomPhrase);
                log.add(randomPhrase);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> listPhrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            listPhrases = in.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPhrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(pathLog, StandardCharsets.UTF_8, true))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/chatLog.txt", "data/botAnswer.txt");
        cc.run();
    }
}