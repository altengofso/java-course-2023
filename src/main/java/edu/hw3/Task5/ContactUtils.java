package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ContactUtils {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    private ContactUtils() {
    }

    public static String getFistNameFromFullName(String fullName) {
        String[] splitted = fullName.split(" ");
        if (splitted.length == 1) {
            return "";
        }
        return splitted[0];
    }

    public static String getLastNameFromFullName(String fullName) {
        String[] splitted = fullName.split(" ");
        if (splitted.length == 1) {
            return fullName;
        }
        return splitted[1];
    }

    public static Contact[] parseContacts(String[] contacts, String sortOrder) {
        if (!sortOrder.equals(ASC) && !sortOrder.equals(DESC)) {
            throw new IllegalArgumentException("Invalid sort order. Allowed %s or %s".formatted(ASC, DESC));
        }

        if (contacts == null || contacts.length == 0) {
            return new Contact[0];
        }

        List<Contact> contactList = new ArrayList<>();
        for (String contact : contacts) {
            contactList.add(new Contact(contact));
        }

        if (sortOrder.equals(ASC)) {
            contactList.sort(Comparator.naturalOrder());
        } else {
            contactList.sort(Comparator.reverseOrder());
        }

        return contactList.toArray(new Contact[0]);
    }
}
