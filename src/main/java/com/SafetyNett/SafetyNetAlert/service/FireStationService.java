package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FireStationService {

    FireStationRepository fireStationsRepository;


    @Autowired
    public FireStationService(FireStationRepository fireStationsRepository) {

        this.fireStationsRepository = fireStationsRepository;
    }

    public List<FireStation> getFireStationsByNumber(int stationNumber) {
        log.debug("Call getFireStationsByNumber with {}",stationNumber);
        List<FireStation> fireStationsResult = new ArrayList<>();
        List<FireStation> fireStations = fireStationsRepository.findAll();
        for(FireStation fireStation : fireStations) {
            if (fireStation.getStation() == stationNumber) {
                fireStationsResult.add(fireStation);

            }
        }
        log.debug("Response to getFireStationsByNumber with {} : {}",stationNumber,fireStationsResult);

        return fireStationsResult;
    }

    public List<String> getAllAdressAssociatedToFireStations(List<Integer> stations) {
        log.debug("Call getAllAdressAssociatedToFireStations with {}",stations);
        List<String> addresses = new ArrayList<>();
        List<FireStation> fireStations = fireStationsRepository.findAll();
        for(FireStation fireStation : fireStations) {
            for(Integer station : stations) {
                if (fireStation.getStation() == station) {
                    addresses.add(fireStation.getAddress());
                }
            }
        }
        log.debug("Response to getAllAdressAssociatedToFireStations with {} : {}",stations,addresses);
        return addresses;
    }


    public int getFireStationsNumberByAdress(String address) {
        log.debug("Call getFireStationsNumberByAdress with {}",address);

        List<FireStation> fireStations = fireStationsRepository.findAll();
        int fireStationNumber = 0;


        for(FireStation fireStation : fireStations) {
            if (fireStation.getAddress().equals(address)) {
                log.debug("Response to getFireStationsNumberByAdress with {} : {}",address,fireStationNumber);
                fireStationNumber = fireStation.getStation();
            }
            log.debug("Failed to getFireStationsNumberByAdress with {}",address);

        }
        return fireStationNumber;


    }


}

