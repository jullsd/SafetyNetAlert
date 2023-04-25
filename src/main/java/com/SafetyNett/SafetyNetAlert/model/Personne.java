package com.SafetyNett.SafetyNetAlert.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

import java.util.Objects;

@Data
@EqualsAndHashCode
public class Personne {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;


    public Personne() {
    }

    @Override
    public String toString() {
        return "Personne{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Personne(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = ( Personne ) o;
        return firstName.equals(personne.firstName) && lastName.equals(personne.lastName) && address.equals(personne.address) && city.equals(personne.city) && zip.equals(personne.zip) && phone.equals(personne.phone) && email.equals(personne.email);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, city, zip, phone, email);
    }


}
