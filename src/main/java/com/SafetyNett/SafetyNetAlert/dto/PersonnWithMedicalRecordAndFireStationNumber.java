package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Generated;

import java.util.List;

@Generated
public class PersonnWithMedicalRecordAndFireStationNumber {

    public PersonnWithMedicalRecordAndFireStationNumber(int fireStationNumber, List<PersonnWithMedicalRecordDTO> personnWithMedicalRecordDTOSs) {
        this.fireStationNumber = fireStationNumber;
        this.personnWithMedicalRecordDTOSs = personnWithMedicalRecordDTOSs;
    }

    private int fireStationNumber;
    private List<PersonnWithMedicalRecordDTO> personnWithMedicalRecordDTOSs;

}
