package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class FireStationRepositoryDataMemory implements FireStationRepository {


    private List<FireStation> fireStations = new ArrayList<>();

    @Autowired
    public FireStationRepositoryDataMemory(DataReaderFromAJson dataReaderFromAJson) {
        fireStations.addAll(dataReaderFromAJson.fireStations());

    }

    @Override
    public List<FireStation> findAll() {



        List<FireStation> fireStationsclone = new ArrayList<>(fireStations);

        log.debug("Response to findAll(): {}" , fireStationsclone);

        return fireStationsclone;

    }

    @Override
    public FireStation addAFireStation(FireStation fireStation) {

        fireStations.add(fireStation);


        return fireStation;

    }

    @Override
    public FireStation findByAdresse(String adress) {
        log.debug("Call findByAdresse with {}",adress);
        for(FireStation fireStation : fireStations) {
            if ((fireStation.getAddress()).equals(adress)) {
                log.debug("Response to findByAdresse  {}",fireStation);
                return fireStation;
            }
        }
        log.debug("Failed to findByLastNameAndFirstName with {}",adress);
        throw new IllegalArgumentException("Unkown lastName or address");

    }

    @Override
    public void deleteAFireStation(FireStation fireStation) {


        fireStation = findByAdresse(fireStation.getAddress());

        fireStations.remove(fireStation);


    }

    @Override
    public FireStation udapteAFireStaion(FireStation fireStation) {
        fireStation = findByAdresse(fireStation.getAddress());
        int fireStationIndex = fireStations.indexOf(findByAdresse(fireStation.getAddress()));
        fireStations.set(fireStationIndex, fireStation);


        return fireStation;
    }

}
