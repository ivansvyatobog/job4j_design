package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket client = server.accept();
                try (OutputStream out = client.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(client.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("Hello")) {
                        out.write("Hello".getBytes());
                    } else if (str.contains("Exit")) {
                        out.write("Работа сервера завершена!".getBytes());
                        server.close();
                    } else {
                        out.write("What".getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}