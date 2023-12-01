package edu.hw6.Task6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class KnownPorts {
    private static final String PORTS_CSV_FILE = "src/main/resources/hw6/Task6/service-names-port-numbers.csv";
    private final HashMap<String, String> ports;

    public KnownPorts() {
        this.ports = readPortsFromFile();
    }

    public String getService(int port, String protocol) {
        return ports.getOrDefault(port + protocol.toLowerCase(), "");
    }

    @SuppressWarnings("MagicNumber")
    private HashMap<String, String> readPortsFromFile() {
        HashMap<String, String> map = new HashMap<>();
        try {
            var list = Files.readAllLines(Path.of(PORTS_CSV_FILE));
            for (int i = 1; i < list.size(); ++i) {
                String[] line = list.get(i).split(";");
                String port = line[1];
                String protocol = line[2].toLowerCase();
                String service = line[0];
                map.put(port + protocol, service);
            }
            return map;
        } catch (IOException e) {
            return null;
        }
    }
}
