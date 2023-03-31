package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class FireStationRepositoryDataMemory implements  FireStationRepository{

    @Autowired
    DataReaderFromAJson dataReaderFromAJson;

    private List<FireStation> fireStations = new ArrayList<>();

    public FireStationRepositoryDataMemory(DataReaderFromAJson dataReaderFromAJson) {
        this.dataReaderFromAJson = dataReaderFromAJson;
        fireStations.addAll(dataReaderFromAJson.fireStations());

    }

    @Override
    public List<FireStation> findAll() {

        log.info("asas");

        List<FireStation> fireStationsclone = new ArrayList<>(fireStations);

        return fireStationsclone;

    }

    @Override
    public FireStation addAFireStation(FireStation fireStation) {

        fireStations.add(fireStation);

        return fireStation;
    }

    @Override
    public FireStation findByAdresse(String adress) {
        for(FireStation fireStation : fireStations) {
            if ((fireStation.getAddress()).equals(adress)) {
                return fireStation;
            }
        }
        return null;
    }

    @Override
    public List<FireStation> deleteAPersonne(FireStation fireStation) {
        findByAdresse(fireStation.getAddress());

        fireStations.remove(fireStation);

        return fireStations;

    }

    @Override
    public FireStation udapteAFireStaion(FireStation fireStation) {
        findByAdresse(fireStation.getAddress());
        int personneIndex= fireStations.indexOf(findByAdresse(fireStation.getAddress()));
        fireStations.set(personneIndex,fireStation);


        return fireStation;
    }

}
