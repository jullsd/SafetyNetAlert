package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonnWithMedicalRecordDTO {



    private String lastName;

    public PersonnWithMedicalRecordDTO(String lastName, List<String> medications, List<String> allergies, int birthdate, String phoneNumber) {
        this.lastName = lastName;
        this.medications = medications;
        this.allergies = allergies;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
    }

    private List<String> medications;

    private List<String> allergies;

    private int birthdate;

    private String phoneNumber;


}
