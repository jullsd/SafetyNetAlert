package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
public class PersonWithMedicalRecordDto {

    private String lastName;

    private String phone;

    private int age;

    private List<String> medications;

    private List<String> allergies;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMedications() {
        return new ArrayList<>(medications);
    }

    public void setMedications(List<String> medications) {
        this.medications = new ArrayList<>(medications);
    }

    public List<String> getAllergies() {
        return new ArrayList<>(allergies);
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = new ArrayList<>(allergies);
    }


    public PersonWithMedicalRecordDto(String lastName, String phone, int age, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = new ArrayList<>(medications);
        this.allergies = new ArrayList<>(allergies);
    }


}
