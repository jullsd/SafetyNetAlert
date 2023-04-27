package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepository;
import com.SafetyNett.SafetyNetAlert.service.PersonneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Personne> addANewPerson(@RequestBody Personne personne) {
        log.info("Call of addANewPerson with  person : {}", personne);
        try {
            log.info("Add  Person : {}", personne);
            personne = personneRepository.addNewPersonne(personne);
            return new ResponseEntity<>(personne, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Failed to Add person  with {}", personne);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping( "/person" )
    public ResponseEntity<HttpStatus> deleteAPerson(@RequestBody Personne personne) {
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
    public ResponseEntity<Personne> updateAperson(@RequestBody Personne personne) {

        log.info("Call of updateAperson with personne : {}", personne);
        try {
            log.info("update personne : {}", personne);
            personne = personneRepository.udapteInformationOfaPersonne(personne);
            return new ResponseEntity<>(personne, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Failed to update a person  with {}", personne);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping( "/communityEmail" )
    public ResponseEntity<List<String>> emailAssociatedToACity(@RequestParam final String city) {
        log.info("Call of emailAssociatedToACity with city : {}", city);

        try {
            List<String> emailOfPersonInTheCity = personneService.getEmailOfPersonneByCity(city);
            log.info("Response to emailAssociatedToACity : {}", emailOfPersonInTheCity);
            return new ResponseEntity<>(emailOfPersonInTheCity, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to get emailAssociatedToACitys with {}", city);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }
    }
}



