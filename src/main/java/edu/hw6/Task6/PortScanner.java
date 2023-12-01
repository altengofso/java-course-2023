package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortScanner {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 49151;
    private static final String TCP = "TCP";
    private static final String UDP = "UDP";
    private final KnownPorts knownPorts;

    public PortScanner() {
        this.knownPorts = new KnownPorts();
    }

    public void printListenPorts() {
        LOGGER.info("%10s %10s %16s".formatted("Протокол", "Порт", "Сервис"));
        for (Port port : getListenPorts()) {
            LOGGER.info("%10s %10d %16s".formatted(port.protocol(), port.port(), port.service()));
        }
    }

    public List<Port> getListenPorts() {
        List<Port> portList = new ArrayList<>();
        for (int port = LOWER_BOUND; port <= UPPER_BOUND; ++port) {
            if (isTCPPortListening(port)) {
                portList.add(new Port(TCP, port, knownPorts.getService(port, TCP)));
            }
            if (isUDPPortListening(port)) {
                portList.add(new Port(UDP, port, knownPorts.getService(port, UDP)));
            }
        }
        return portList;
    }

    public boolean isTCPPortListening(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    public boolean isUDPPortListening(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}
