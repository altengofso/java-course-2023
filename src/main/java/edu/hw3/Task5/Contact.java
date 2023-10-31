package edu.hw3.Task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import static edu.hw3.Task5.ContactUtils.getFistNameFromFullName;
import static edu.hw3.Task5.ContactUtils.getLastNameFromFullName;

public record Contact(String firstName, String lastName) implements Comparable<Contact> {

    public Contact(String fullName) {
        this(getFistNameFromFullName(fullName), getLastNameFromFullName(fullName));
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
