package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
public class PerssonesAtOneAdresseDto {

    private String address;
    private List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos;

    public PerssonesAtOneAdresseDto(String address, List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtos) {
        this.address = address;
        this.personsWithMedicalRecordDtos = personsWithMedicalRecordDtos;
    }


}
