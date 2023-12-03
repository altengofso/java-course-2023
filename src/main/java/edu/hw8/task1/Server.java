package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public class Server {
    private static final int MAX_CONNECTIONS = 5;
    private final ServerSocket serverSocket;
    private AtomicBoolean isAlive;
    private final ExecutorService executorService;
    private static final Map<String, String> RESPONSE_MAP = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );
    private static final String DEFAULT_RESPONSE = "Извини, я не понял вопроса.";

    @SneakyThrows
    public Server(int port) {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    @SneakyThrows
    public void start() {
        isAlive = new AtomicBoolean(true);
        while (isAlive.get()) {
            try {
                Socket client = serverSocket.accept();
                executorService.submit(() -> handleClient(client));
            } catch (SocketException e) {
                if (isAlive.get()) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @SneakyThrows
    public void stop() {
        isAlive.set(false);
        executorService.close();
        serverSocket.close();
    }

    @SneakyThrows
    private void handleClient(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            StringBuilder request = new StringBuilder();
            request.append(in.readLine());
            while (in.ready()) {
                request.append(in.readLine());
            }
            String response = RESPONSE_MAP.getOrDefault(request.toString(), DEFAULT_RESPONSE);
            out.write(response);
            out.newLine();
            out.flush();
            client.close();
        }
    }
}
