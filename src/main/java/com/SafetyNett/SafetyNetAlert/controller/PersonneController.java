package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.dto.ChildrenDto;
import com.SafetyNett.SafetyNetAlert.dto.ChildrenDtos;
import com.SafetyNett.SafetyNetAlert.dto.PerssonesAtOneAdresseDto;
import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepository;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepositoryDataMemory;
import com.SafetyNett.SafetyNetAlert.service.PersonneService;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PersonneController {

    @Autowired
    PersonneRepository personneRepository;

    @Autowired
    PersonneService personneService;

    @PostMapping( "/person" )
    public ResponseEntity<Personne> addANewPersonne(@RequestBody Personne personne) {
        log.info("Call of addAMedicalRecord with  medicalRecord : {}", personne);
        try {
            log.info("Add  Personne : {}", personne);
            personne = personneRepository.addNewPersonne(personne);
            return new ResponseEntity<>(personne, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Failed to Add medicalRecord  with {}", personne);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping( "/person" )
    public ResponseEntity<HttpStatus> deleteAPersonne(@RequestBody Personne personne) {
        log.info("Call of deleteAPerson  person : {}", personne);
        try {
            log.info("Delete person: {} ", personne);
            personneRepository.deleteAPersonne(personne);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error("Failed to delete person with {}", personne);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping( "/person" )
    public ResponseEntity<Personne> udapteApersonne(@RequestBody Personne personne) {

        log.info("Call of udapteApersonne with personne : {}", personne);
        try {
            log.info("Udapte personne : {}", personne);
            personne = personneRepository.udapteInformationOfaPersonne(personne);
            return new ResponseEntity<>(personne, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Failed to udapte a personne medicalRecord  with {}", personne);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping( "/communityEmail" )
    public ResponseEntity<List<String>> emailAssociatedToACity(@RequestParam final String city) {
        log.info("Call of emailAssociatedToACity with city : {}", city);

        try {
            List<String> emailOfPersonInTheCity = personneService.getEmailOfPersonneByCity(city);
            log.info("Response to emailAssociatedToACity : {}", emailOfPersonInTheCity );
            return new ResponseEntity<>(emailOfPersonInTheCity, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to getPersonnesAtOneAdressesAssociatedtoFiresStations with {}", city);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }
    }
}



