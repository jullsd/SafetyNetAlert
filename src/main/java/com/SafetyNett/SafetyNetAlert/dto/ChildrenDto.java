package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Data;
import lombok.Generated;


@Data
@Generated
public class ChildrenDto {


    private String firstName;

    private String lastName;

    private int age;

    public ChildrenDto(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
