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

    private final String OTHER_FIRE_STATION_ADRESS = "ee";
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

    @Test
    void getAllAdressAssociatedToFireStations() {
        FireStationService fireStationService = new FireStationService(fireStationRepository);
        List<Integer> stations =  new ArrayList<>();

        List<FireStation> fireStations =  new ArrayList<>();
        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER_1);
        fireStations.add(fireStation);
        stations.add(FIRE_STATION_NUMBER_1);

        when(fireStationRepository.findAll()).thenReturn(fireStations);

        assertThat(fireStationService.getAllAdressAssociatedToFireStations(stations)).contains(FIRE_STATION_ADRESS);


    }

    @Test
    void getAllAdressAssociatedToFireStationsWithBadstationsNumber() {
        FireStationService fireStationService = new FireStationService(fireStationRepository);
        List<Integer> stations =  new ArrayList<>();

        List<FireStation> fireStations =  new ArrayList<>();
        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER_1);
        fireStations.add(fireStation);
        stations.add(FIRE_STATION_NUMBER_2);

        when(fireStationRepository.findAll()).thenReturn(fireStations);

        assertThat(fireStationService.getAllAdressAssociatedToFireStations(stations)).doesNotContain(FIRE_STATION_ADRESS);
        assertThat(fireStationService.getAllAdressAssociatedToFireStations(stations)).isEmpty();


    }


    @Test
    void getFireStationsNumberByAdress() {

        FireStationService fireStationService = new FireStationService(fireStationRepository);

        List<FireStation> fireStations =  new ArrayList<>();

        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER_1);
        fireStations.add(fireStation);

        when(fireStationRepository.findAll()).thenReturn(fireStations);

        assertThat(fireStationService.getFireStationsNumberByAdress(FIRE_STATION_ADRESS)).isEqualTo(FIRE_STATION_NUMBER_1);

    }

    @Test
    void getFireStationsNumberByBadAdress() {

        FireStationService fireStationService = new FireStationService(fireStationRepository);

        List<FireStation> fireStations =  new ArrayList<>();

        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER_1);
        fireStations.add(fireStation);

        when(fireStationRepository.findAll()).thenReturn(fireStations);

        assertThat(fireStationService.getFireStationsNumberByAdress(OTHER_FIRE_STATION_ADRESS)).isNotEqualTo(FIRE_STATION_NUMBER_1);

    }


}
