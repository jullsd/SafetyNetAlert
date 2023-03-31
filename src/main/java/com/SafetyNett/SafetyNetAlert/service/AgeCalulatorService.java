package com.SafetyNett.SafetyNetAlert.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
@Service
public class AgeCalulatorService {

    private static final int MAJORITE = 18;

    public int ageCalculation(String birthdate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birthdate, format);
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears();
    }

    public boolean isAdult(String birthday) {

        if (ageCalculation(birthday) > MAJORITE) {
            return true;
        }
        return false;
    }
    public boolean isChild(String birthday) {
        if (ageCalculation(birthday) <= MAJORITE) {
            return true;
        }
        return false;
    }

}
