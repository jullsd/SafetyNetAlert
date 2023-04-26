package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService {

    FireStationRepository fireStationsRepository;


    @Autowired
    public FireStationService(FireStationRepository fireStationsRepository) {

        this.fireStationsRepository = fireStationsRepository;
    }

    public List<FireStation> getFireStationsByNumber(int stationNumber) {
        List<FireStation> fireStationsResult = new ArrayList<>();
        List<FireStation> fireStations = fireStationsRepository.findAll();
        for(FireStation fireStation : fireStations) {
            if (fireStation.getStation() == stationNumber) {
                fireStationsResult.add(fireStation);

            }
        }

        return fireStationsResult;
    }

    public List<String> getAllAdressAssociatedToFireStations(List<Integer> stations) {
        List<String> addresses = new ArrayList<>();
        List<FireStation> fireStations = fireStationsRepository.findAll();
        for(FireStation fireStation : fireStations) {
            for(Integer station : stations) {
                if (fireStation.getStation() == station) {
                    addresses.add(fireStation.getAddress());
                }
            }
        }
        return addresses;
    }


    public int getFireStationsNumberByAdress(String address) {

        List<FireStation> fireStations = fireStationsRepository.findAll();
        int fireStationNumber = 0;


        for(FireStation fireStation : fireStations) {
            if (fireStation.getAddress().equals(address)) {

                fireStationNumber = fireStation.getStation();
            }

        }
        return fireStationNumber;


    }


}

