package com.SafetyNett.SafetyNetAlert.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MedicalRecord {

    private String firstName;

    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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

    public MedicalRecord() {
    }

    public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = new ArrayList<>(medications);
        this.allergies = new ArrayList<>(allergies);
    }

    @Override
    public String toString() {
        return "MedicalRecords{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecord that = ( MedicalRecord ) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && birthdate.equals(that.birthdate) && medications.equals(that.medications) && allergies.equals(that.allergies);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthdate, medications, allergies);
    }





}


