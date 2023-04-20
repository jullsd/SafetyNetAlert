package com.SafetyNett.SafetyNetAlert.dto;

import com.SafetyNett.SafetyNetAlert.model.Personne;
import lombok.Data;
import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

@Data
@Generated
public class ChildrenDto {


    private String firstName;

    private String lastName;

    private String birthDate;

    public ChildrenDto(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

}
