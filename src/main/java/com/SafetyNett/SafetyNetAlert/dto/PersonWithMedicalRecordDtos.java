package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
public class PersonWithMedicalRecordDtos {

    private int fireStationNumber;
    private List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos;

    public PersonWithMedicalRecordDtos(int fireStationNumber, List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos) {
        this.fireStationNumber = fireStationNumber;
        this.personsWithMedicalRecordDtos = new ArrayList<>(personsWithMedicalRecordDtos);
    }

    public int getFireStationNumber() {
        return fireStationNumber;
    }

    public void setFireStationNumber(int fireStationNumber) {
        this.fireStationNumber = fireStationNumber;
    }

    public List<PersonWithMedicalRecordDto> getPersonsWithMedicalRecordDtos() {
        return new ArrayList<>(personsWithMedicalRecordDtos);
    }

    public void setPersonsWithMedicalRecordDtos(List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos) {
        this.personsWithMedicalRecordDtos = new ArrayList<>(personsWithMedicalRecordDtos);
    }


}
