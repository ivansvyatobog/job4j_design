package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(target)) {
            boolean statusCheck = true;
            String read;
            while ((read = in.readLine()) != null) {
                if (statusCheck && (read.startsWith("400") || read.startsWith("500"))) {
                    statusCheck = false;
                    out.append(read.split(" ")[1]).append(";");
                } else if (!statusCheck && read.startsWith("200") || read.startsWith("300")) {
                    statusCheck = true;
                    out.append(read.split(" ")[1]);
                    out.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}