package edu.hw6.Task6;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

public class PortScannerTest {
    @Test
    void testPortScanner() {
        PortScanner portScanner = Mockito.spy(new PortScanner());
        Mockito.when(portScanner.isUDPPortListening(53)).thenReturn(true);
        Mockito.when(portScanner.isTCPPortListening(80)).thenReturn(true);
        var ports = portScanner.getListenPorts();
        assertThat(ports).contains(new Port("UDP", 53, "domain"));
        assertThat(ports).contains(new Port("TCP", 80, "http"));
    }
}
