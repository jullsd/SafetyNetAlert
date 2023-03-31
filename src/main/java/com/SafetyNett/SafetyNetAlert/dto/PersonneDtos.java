package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data

public class PersonneDtos {
    private List<PersonneDto> personnes;
    private int adultCount;
    private int childrenCount;

    public PersonneDtos(List<PersonneDto> personnes, int adultCount, int childrenCount) {
        this.personnes = personnes;
        this.adultCount = adultCount;
        this.childrenCount = childrenCount;
    }
}
