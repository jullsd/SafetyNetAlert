package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.dto.ChildrenDto;
import com.SafetyNett.SafetyNetAlert.dto.ChildrenDtos;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepository;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepositoryDataMemory;
import com.SafetyNett.SafetyNetAlert.service.PersonneService;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
public class PersonneController {

    @Autowired
    PersonneRepository personneRepository;

    @Autowired
    PersonneService personneService;

    @PostMapping( "/person" )
    public ResponseEntity addANewPersonne(@RequestBody Personne personne) {

        personneRepository.addNewPersonne(personne);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/person/{firstName}/{lastName}")
        public void deleteAPersonne(@RequestBody Personne personne) {

        personneRepository.deleteAPersonne(personne); }



        @PutMapping("/person/")
                public  Personne udapteApersonne(@RequestBody Personne personne) {

            personneRepository.udapteInformationOfaPersonne(personne);

            return personne;
    }

   @GetMapping("/communityEmail")
    public List<String> emailAssociatedToACity(@RequestParam final String city) {

        return personneService.getEmailOfPersonneByCity(city);

    }


}



