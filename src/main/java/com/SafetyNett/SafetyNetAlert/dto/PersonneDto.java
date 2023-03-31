package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;

@Data
public class PersonneDto {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String zip;
    private String city;

    public PersonneDto(String firstName, String lastName, String address, String phone, String zip, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.zip = zip;
        this.city = city;
    }













}
