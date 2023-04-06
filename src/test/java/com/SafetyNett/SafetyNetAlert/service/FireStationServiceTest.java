package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class)
class FireStationServiceTest {

@Mock
private  FireStationRepositoryDataMemory fireStationRepository;

    private final String FIRE_STATION_ADRESS = "aa";
    private final int FIRE_STATION_NUMBER_1 = 1;

    private final int FIRE_STATION_NUMBER_2 = 2;

    @Test
    void getFireStationsByNumber() {

        FireStationService fireStationService = new FireStationService(fireStationRepository);


        List<FireStation> fireStations =  new ArrayList<>();
        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER_1);
        fireStations.add(fireStation);


        when(fireStationRepository.findAll()).thenReturn(fireStations);


    assertThat(fireStationService.getFireStationsByNumber(FIRE_STATION_NUMBER_1)).contains(fireStation);


    }

    @Test
    void dontGetFireStationsByNumber() {

        FireStationService fireStationService = new FireStationService(fireStationRepository);


        List<FireStation> fireStations =  new ArrayList<>();
        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER_1);
        fireStations.add(fireStation);


        when(fireStationRepository.findAll()).thenReturn(fireStations);


        assertThat(fireStationService.getFireStationsByNumber(FIRE_STATION_NUMBER_2)).doesNotContain(fireStation);


    }
}
