package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.dto.PersonneDtos;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepositoryDataMemory;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@ComponentScan
public class FireStationsController {

    @Autowired
    FireStationRepositoryDataMemory fireStationRepository;
    @PostMapping("/firestation")
    public ResponseEntity addAFireStation(@RequestBody FireStation fireStation){

        fireStationRepository.addAFireStation(fireStation);

        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/firestation")
    public void deleteAFireStation(@RequestBody FireStation fireStation){
        fireStationRepository.deleteAPersonne(fireStation);
    }

    @PutMapping("/firestation")
    public FireStation udapteAFireStation(@RequestBody FireStation fireStation) {

        fireStationRepository.udapteAFireStaion(fireStation);

        return fireStation;

    }


}
