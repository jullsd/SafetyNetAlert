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
@ComponentScan
@Slf4j
public class FireStationsController {

   @Autowired
    FireStationRepository fireStationRepository;
    @PostMapping("/firestation")
    public ResponseEntity<HttpStatus> addAFireStation(@RequestBody FireStation fireStation){

        fireStationRepository.addAFireStation(fireStation);

        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/firestation")
    public ResponseEntity deleteAFireStation(@RequestBody FireStation fireStation){
        try {
            fireStationRepository.deleteAFireStation(fireStation);
            log.info("fireStation removed");

        }
        catch (Exception e) {
            log.error("fireStation not removed");

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/firestation")
    public ResponseEntity udapteAFireStation(@RequestBody FireStation fireStation) {

        fireStationRepository.udapteAFireStaion(fireStation);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }


}
