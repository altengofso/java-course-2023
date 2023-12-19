package edu.hw8.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServerTest {
    private final String endpoint = "localhost";
    private final int port = 8080;

    static Stream<Arguments> ClientServerArguments() {
        return Stream.of(
            Arguments.of("личности", "Не переходи на личности там, где их нет")
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
        serverThread.interrupt();
    }
}
