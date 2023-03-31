package com.SafetyNett.SafetyNetAlert.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.Assertions.assertThat;


class AgeCalulatorServiceTest {

    private static AgeCalulatorService ageCalulatorService;
    private final String BIRTHDATE = "04/14/1991";

    @BeforeAll
    public static void setUp() {
        ageCalulatorService = new AgeCalulatorService();
    }

    @Test
    public void ageCalculation() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(BIRTHDATE, format);
        LocalDate today = LocalDate.now();

        int ageExepted =  Period.between(birthDate, today).getYears();

      int ageAssociatedToABirthDate = ageCalulatorService.ageCalculation(BIRTHDATE);

      assertThat(ageAssociatedToABirthDate).isEqualTo(ageExepted);


    }

    @Test
    public void isAdult() {


    }

    @Test
    public void isChild() {
    }
}