package edu.hw3.Task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {
    private final String firstName;
    private final String lastName;

    public Contact(String fullName) {
        String[] splitted = splitFullName(fullName);
        this.firstName = splitted[0];
        this.lastName = splitted[1];

    }

    private String[] splitFullName(String fullName) {
        String[] splitted = fullName.split(" ");
        if (splitted.length == 1) {
            return new String[] {"", fullName};
        }
        return splitted;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "%s %s".formatted(firstName, lastName);
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(getFirstName(), contact.getFirstName())
            && Objects.equals(getLastName(), contact.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        return getLastName().compareTo(o.getLastName());
    }
}
