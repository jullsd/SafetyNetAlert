package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;
import lombok.Generated;

import java.util.List;
@Data
@Generated
public class PersonInfoWithMedicalRecordDTO {


    private String lastName;

    private String address;

    private int age;

    private String email;

    private List<String> medications;

    private List<String> allergies;

    public PersonInfoWithMedicalRecordDTO(String lastName, String address, int age, String email, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
        this.medications = medications;
        this.allergies = allergies;
    }

}
