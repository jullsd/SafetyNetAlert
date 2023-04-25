package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.dto.PersonneDtos;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepository;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepositoryDataMemory;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@Slf4j
public class FireStationsController {

    @Autowired
    FireStationRepository fireStationRepository;

    @PostMapping( "/firestation" )
    public ResponseEntity<FireStation> addAFireStation(@RequestBody FireStation fireStation) {
        log.info("Call of addAFireStation with  fireStation : {}", fireStation);
        try {
            fireStation = fireStationRepository.addAFireStation(fireStation);
            log.info("Add firestation : {} ", fireStation);
            return new ResponseEntity<>(fireStation, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Failed to Add firestation  with {}", fireStation);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping( "/firestation" )
    public ResponseEntity<HttpStatus> deleteAFireStation(@RequestBody FireStation fireStation) {
        log.info("Call of deleteAFireStation with  fireStation : {}", fireStation);
        try {
            fireStationRepository.deleteAFireStation(fireStation);
            log.info("Delete firestation : {} ", fireStation);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to delete firestation with {}", fireStation);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping( "/firestation" )
    public ResponseEntity<FireStation> udapteAFireStation(@RequestBody FireStation fireStation) {
        log.info("Call of deleteAFireStation with  fireStation : {}", fireStation);
        try {
            fireStation = fireStationRepository.udapteAFireStaion(fireStation);
            log.info("Udapte firestation : {} ", fireStation);

            return new ResponseEntity<>(fireStation, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            log.error("Failed to  udapte with {}", fireStation);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
