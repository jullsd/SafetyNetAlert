package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
public class PersonWithMedicalRecordDtos {


    public PersonWithMedicalRecordDtos(int fireStationNumber, List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos) {
        this.fireStationNumber = fireStationNumber;
        this.personsWithMedicalRecordDtos = personsWithMedicalRecordDtos;
    }

    private int fireStationNumber;
    private List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos;

}
