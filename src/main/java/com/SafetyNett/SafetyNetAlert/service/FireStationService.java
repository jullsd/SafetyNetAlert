package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepositoryDataMemory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService {

    DataReaderFromAJson dataReaderFromAJson = new DataReaderFromAJson();
    FireStationRepositoryDataMemory fireStationsRepository = new FireStationRepositoryDataMemory(dataReaderFromAJson);

    /*
    public List<String> phoneNumberLitLinktoACaserne(List<FireStation> fireStations, int fireStationNumber) {

        for(FireStation fireStation : fireStations) {
            if (fireStation.getStation() == fireStationNumber) {

                getPhoneNumberByAdress(fireStation.getAddress());
            }
        }
        return phoneNumbers;
    }

     */

    public FireStation getFireStationByNumber(int fireStationNumber) {
        List<FireStation> fireStations = fireStationsRepository.findAll();
        for(FireStation fireStation : fireStations) {
            if (fireStation.getStation() == fireStationNumber) {
                return fireStation;
            }
        }
        //should return optional instead of null
        return null;
    }

    public List<FireStation>  getFireStationsByNumber(int fireStationNumber) {
        List<FireStation> fireStationsResult =  new ArrayList<>();
        List<FireStation> fireStations = fireStationsRepository.findAll();
        for(FireStation fireStation : fireStations) {
            if (fireStation.getStation() == fireStationNumber) {
                fireStationsResult.add(fireStation);
            }
        }
        //should return optional instead of null
       return fireStationsResult;
    }




}
