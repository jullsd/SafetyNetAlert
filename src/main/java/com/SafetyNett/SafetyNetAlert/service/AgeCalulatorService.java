package com.SafetyNett.SafetyNetAlert.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class AgeCalulatorService {

    @Autowired
    DateService dateService;

    public AgeCalulatorService(DateService dateService) {
        this.dateService = dateService;
    }

    private static final int MAJORITE = 18;

    public int ageCalculation(String birthdate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birthdate, format);
        LocalDate today = dateService.now();
        log.debug("Calculation deltail, birthday : {}. date :{} , between : {}", birthDate, today, Period.between(birthDate, today).getYears());
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
