package com.SafetyNett.SafetyNetAlert.Repository;


import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepositoryDataMemory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith( MockitoExtension.class)
public class FireStationRepositoryTest {

    @Mock
    private static DataReaderFromAJson dataReaderFromAJson;

    private List<FireStation> fireStationsInData = new ArrayList<>();

    private FireStation fireStation = new FireStation("adress",2);

    @Test
    public void GetANewFireStation() {


       FireStationRepositoryDataMemory fireStationRepository= new FireStationRepositoryDataMemory(dataReaderFromAJson);

       fireStationRepository.addAFireStation(fireStation);

        List fireStations = fireStationRepository.findAll();

        assertThat(fireStations).contains(fireStation);
    }

    @Test
    public void DeleteAFireStations() {

        FireStationRepositoryDataMemory fireStationRepository= new FireStationRepositoryDataMemory(dataReaderFromAJson);


        fireStationsInData.add(0,fireStation);

        List fireStations = fireStationRepository.deleteAFireStation(fireStation);

        assertThat(fireStations).doesNotContain(fireStation);
    }

    @Test
    public void findByAdressTest() {

        FireStationRepositoryDataMemory fireStationRepository= new FireStationRepositoryDataMemory(dataReaderFromAJson);
        fireStationRepository.addAFireStation(fireStation);


       FireStation fireStation2 = fireStationRepository.findByAdresse("adress");


        assertThat(fireStation2).isEqualTo(fireStation);

    }

    @Test
    public void DontfindByAdressTest() {

        FireStationRepositoryDataMemory fireStationRepository= new FireStationRepositoryDataMemory(dataReaderFromAJson);

        fireStationRepository.addAFireStation(fireStation);

        FireStation fireStationA = fireStationRepository.findByAdresse("adressNull");

        assertThat(fireStationA).isNull();

    }
    @Test
    public void udapteInformationOfAFireStationTest() {
        FireStationRepositoryDataMemory fireStationRepository= new FireStationRepositoryDataMemory(dataReaderFromAJson);
       fireStationRepository.addAFireStation(fireStation);

       fireStation.setStation(5);
       fireStation.setAddress("LILI");

        fireStationRepository.udapteAFireStaion(fireStation);

        assertThat(fireStation.getAddress()).isEqualTo("LILI");
        assertThat(fireStation.getStation()).isEqualTo(5);

    }



}
