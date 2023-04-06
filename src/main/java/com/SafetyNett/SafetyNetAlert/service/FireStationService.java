package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepositoryDataMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService {

    FireStationRepositoryDataMemory fireStationsRepository;
    @Autowired
    public FireStationService(FireStationRepositoryDataMemory fireStationsRepository) {
        this.fireStationsRepository = fireStationsRepository;
    }

    public List<FireStation>  getFireStationsByNumber(int fireStationNumber) {
        List<FireStation> fireStationsResult =  new ArrayList<>();
        List<FireStation> fireStations = fireStationsRepository.findAll();
        for(FireStation fireStation : fireStations) {
            if (fireStation.getStation() == fireStationNumber) {
                fireStationsResult.add(fireStation);
            }
        }
      
       return fireStationsResult;
    }




}
