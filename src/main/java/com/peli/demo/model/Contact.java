package com.peli.demo.model;

import lombok.Data;

@Data
public class Contact {
    Integer contactId;
    String firstName;
    String lastName;
    String email;
    String zipcode;
    String address;

    public Contact(String[] row) {
        this.contactId = Integer.parseInt(row[0]);
        this.firstName = row[1];
        this.lastName = row[2];
        this.email = row[3];
        this.zipcode = row[4];
        this.address = row[5];
    }

    public String getComparableString() {
        return String.format("%s %s %s %s %s", firstName, lastName, email, zipcode, address);
    }

    @Override
    public String toString() {
        return contactId + ";"
              + firstName + ";"
              + lastName + ";"
              + email + ";"
              + zipcode + ";"
              + address;
    }
}
