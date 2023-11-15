package edu.hw6.Task6;

import java.util.Objects;

public record Port(String protocol, int port, String service) {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Port port1 = (Port) o;
        return port == port1.port && Objects.equals(protocol, port1.protocol) && Objects.equals(service, port1.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocol, port, service);
    }
}
