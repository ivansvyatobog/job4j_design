package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    public static void main(String[] args) {
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
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
    }
}