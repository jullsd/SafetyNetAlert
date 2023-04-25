package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;
import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
public class PerssonesAtOneAdresseDto {


    private String address;
    private List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonWithMedicalRecordDto> getPersonsWithMedicalRecordDtos() {
        return  new ArrayList<>( personsWithMedicalRecordDtos);
    }

    public void setPersonsWithMedicalRecordDtos(List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos) {
        this.personsWithMedicalRecordDtos = new ArrayList<>( personsWithMedicalRecordDtos);
    }


    public PerssonesAtOneAdresseDto(String address, List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos) {
        this.address = address;
        this.personsWithMedicalRecordDtos = new ArrayList<>( personsWithMedicalRecordDtos);
    }


}
