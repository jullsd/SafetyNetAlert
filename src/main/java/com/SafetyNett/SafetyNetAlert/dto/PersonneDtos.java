package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Generated
public class PersonneDtos {

    private List<PersonneDto> personnes;
    private int adultCount;
    private int childrenCount;

    public List<PersonneDto> getPersonnes() {
        return new ArrayList<>(personnes);
    }

    public void setPersonnes(List<PersonneDto> personnes) {
        this.personnes = new ArrayList<>(personnes);
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public PersonneDtos(List<PersonneDto> personnes, int adultCount, int childrenCount) {
        this.personnes = new ArrayList<>(personnes);
        this.adultCount = adultCount;
        this.childrenCount = childrenCount;
    }
}
