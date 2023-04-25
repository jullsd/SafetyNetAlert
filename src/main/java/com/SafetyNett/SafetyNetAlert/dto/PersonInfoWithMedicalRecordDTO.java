package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
public class PersonInfoWithMedicalRecordDTO {


    private String lastName;

    private String address;

    public String getLastName() {
        return lastName;
    }

    private int age;

    private String email;

    private List<String> medications;

    private List<String> allergies;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public PersonInfoWithMedicalRecordDTO(String lastName, String address, int age, String email, List<String> medications, List<String> allergies) {
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
        this.medications = new ArrayList<>(medications);
        this.allergies = new ArrayList<>(allergies);
    }

}
