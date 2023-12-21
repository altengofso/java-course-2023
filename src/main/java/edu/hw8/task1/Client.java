package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import lombok.SneakyThrows;

public class Client {
    private final String endpoint;
    private final int port;

    public Client(String endpoint, int port) {
        this.endpoint = endpoint;
        this.port = port;
    }

    @SneakyThrows
    public String getReponse(String request) {
        try (Socket socket = new Socket(endpoint, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            out.write(request);
            out.newLine();
            out.flush();

            StringBuilder response = new StringBuilder();
            response.append(in.readLine());
            while (in.ready()) {
                response.append(in.readLine());
            }

            return response.toString();
        }
    }
}
