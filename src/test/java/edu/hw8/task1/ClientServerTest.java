package edu.hw8.task1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServerTest {
    private final String endpoint = "localhost";
    private final int port = 12345;

    static Stream<Arguments> ClientServerArguments() {
        return Stream.of(
            Arguments.of("личности", "Не переходи на личности там, где их нет"),
            Arguments.of(
                "оскорбления",
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
            ),
            Arguments.of(
                "глупый",
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
            ),
            Arguments.of("интеллект", "Чем ниже интеллект, тем громче оскорбления"),
            Arguments.of("что-то другое", "Извини, я не понял вопроса.")
        );
    }

    @ParameterizedTest
    @MethodSource("ClientServerArguments")
    void testSingleClient(String request, String response) {
        Server server = new Server(port);
        Thread serverThread = new Thread(server::start);
        serverThread.start();
        Client client = new Client(endpoint, port);
        assertThat(client.getReponse(request)).isEqualTo(response);
        server.stop();
    }

    @ParameterizedTest
    @MethodSource("ClientServerArguments")
    void testMultipleClients(String request, String response) {
        Server server = new Server(port);
        Thread serverThread = new Thread(server::start);
        serverThread.start();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        var clientFutures = Stream
            .generate(
                () -> CompletableFuture.runAsync(
                    () -> {
                        Client client = new Client(endpoint, port);
                        assertThat(client.getReponse(request)).isEqualTo(response);
                    },
                    executorService
                ))
            .limit(10)
            .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(clientFutures).join();
        executorService.close();
        server.stop();
    }
}
