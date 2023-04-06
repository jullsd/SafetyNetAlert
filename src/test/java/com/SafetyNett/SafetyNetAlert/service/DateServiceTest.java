package com.SafetyNett.SafetyNetAlert.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class)
class DateServiceTest {

    @Mock
    private DateService dateService;

    @Test
    void nowTest() {

        when(dateService.now()).thenReturn(LocalDate.of(2001,9,1));

        assertThat(dateService.now()).isEqualTo(LocalDate.of(2001,9,1));

    }
}