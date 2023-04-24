package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
public class PersonWithMedicalRecordDto {

    public PersonWithMedicalRecordDto(String lastName, String phone, int age, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    private String lastName;

    private String phone;

    private int age;

    private List<String> medications;

    private List<String> allergies;

}
