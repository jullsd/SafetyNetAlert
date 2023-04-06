package com.SafetyNett.SafetyNetAlert.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgeCalulatorServiceTest {


    private final String BIRTHDATE = "04/14/1991";
    @Mock
    private  DateService dateService;

    @Test
    public void ageCalculation() {

        AgeCalulatorService ageCalulatorService = new AgeCalulatorService(dateService);

        when(dateService.now()).thenReturn(LocalDate.of(2011,4,14));
       assertThat(ageCalulatorService.ageCalculation(BIRTHDATE)).isEqualTo(20);
    }


    @Test
    public void isAdult()  {

        AgeCalulatorService ageCalulatorService = new AgeCalulatorService(dateService);

        when(dateService.now()).thenReturn(LocalDate.of(2014,9,1));
        assertThat(ageCalulatorService.isAdult(BIRTHDATE)).isTrue();

    }

    @Test
    public void isNotAnAdult() {
        AgeCalulatorService ageCalulatorService = new AgeCalulatorService(dateService);

        when(dateService.now()).thenReturn(LocalDate.of(2001,9,1));

        assertThat(ageCalulatorService.isAdult(BIRTHDATE)).isFalse();

    }

    @Test
    public void isNotAChild() {
        AgeCalulatorService ageCalulatorService = new AgeCalulatorService(dateService);

        when(dateService.now()).thenReturn(LocalDate.of(2014,9,1));

        assertThat(ageCalulatorService.isChild(BIRTHDATE)).isFalse();

    }
    @Test
    public void isAChild() {
        AgeCalulatorService ageCalulatorService = new AgeCalulatorService(dateService);

        when(dateService.now()).thenReturn(LocalDate.of(2001,9,1));

        assertThat(ageCalulatorService.isChild(BIRTHDATE)).isTrue();

    }


}