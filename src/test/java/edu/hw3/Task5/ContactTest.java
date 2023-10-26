package edu.hw3.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ContactTest {
    @Test
    @DisplayName("Test Contact Constructor")
    void testContactConstructor() {
        Contact contact = new Contact("Abc Def");
        assertThat(contact.getFirstName()).isEqualTo("Abc");
        assertThat(contact.getLastName()).isEqualTo("Def");
    }

    @Test
    @DisplayName("Test Contact Constructor Only lastName")
    void testContactConstructorOnlyLastName() {
        Contact contact = new Contact("Def");
        assertThat(contact.getFirstName()).isEqualTo("");
        assertThat(contact.getLastName()).isEqualTo("Def");
    }

    @Test
    @DisplayName("Test Contact Equal")
    void testContactEqual() {
        Contact contact1 = new Contact("Abc Def");
        Contact contact2 = new Contact("Abc Def");
        assertThat(contact1).isEqualTo(contact1);
        assertThat(contact1).isEqualTo(contact2);
    }

    @Test
    @DisplayName("Test Contact Not Equal")
    void testContactNotEqual() {
        Contact contact1 = new Contact("Abc Def");
        Contact contact2 = new Contact("Def Abc");
        assertThat(contact1).isNotEqualTo(contact2);
        assertThat(contact1).isNotEqualTo(new Object());
    }

    @Test
    @DisplayName("Test Contact Compare")
    void testContactCompare() {
        Contact contact1 = new Contact("Abc Def");
        Contact contact2 = new Contact("Def Ghi");
        assertThat(contact1.compareTo(contact2)).isLessThan(0);
    }
}
