package edu.hw3.Task5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw3.Task5.ContactUtils.parseContacts;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParseContactsTest {
    static Stream<Arguments> ParseContactsArguments() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                "ASC",
                new Contact[] {
                    new Contact("Thomas Aquinas"),
                    new Contact("Rene Descartes"),
                    new Contact("David Hume"),
                    new Contact("John Locke")
                }
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                "DESC",
                new Contact[] {new Contact("Carl Gauss"),
                    new Contact("Leonhard Euler"),
                    new Contact("Paul Erdos")}
            ),
            Arguments.of(new String[] {}, "DESC", new Contact[0]),
            Arguments.of(null, "DESC", new Contact[0])
        );
    }

    @ParameterizedTest
    @MethodSource("ParseContactsArguments")
    @DisplayName("Test Parse Contacts")
    void testParseContacts(String[] contacts, String sortOrder, Contact[] contactList) {
        assertThat(parseContacts(contacts, sortOrder)).isEqualTo(contactList);
    }

    static Stream<Arguments> ParseContactsIllegalArgumentExceptionArguments() {
        return Stream.of(
            Arguments.of(new String[] {}, ""),
            Arguments.of(new String[] {}, "A"),
            Arguments.of(new String[] {}, "D")
        );
    }

    @ParameterizedTest
    @MethodSource("ParseContactsIllegalArgumentExceptionArguments")
    @DisplayName("Test Parse Contacts IllegalArgumentException")
    void testParseContactsIllegalArgumentException(String[] contacts, String sortOrder) {
        assertThrows(IllegalArgumentException.class, () -> parseContacts(contacts, sortOrder));
    }
}
