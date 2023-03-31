package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.dto.ChildrenDto;
import com.SafetyNett.SafetyNetAlert.dto.ChildrenDtos;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepositoryDataMemory;
import com.SafetyNett.SafetyNetAlert.service.PersonneService;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
public class PersonneController {

    @Autowired
    PersonneRepositoryDataMemory personneRepository;

    @Autowired
    PersonneService personneService;

    @Autowired
    SafetyAlertService safetyAlertService;


    @GetMapping( "/Personnes" )
    public List<Personne> listPersonne() {
        return personneRepository.findAll();
    }

    @PostMapping( "/Personnes" )
    public void addANewPersonne(@RequestBody Personne personne) {

        personneRepository.addNewPersonne(personne);
    }

    @DeleteMapping("/Personnes/{firstName}/{lastName}")
        public void deleteAPersonne(@RequestBody Personne personne) {

        personneRepository.deleteAPersonne(personne); }



        @PutMapping("/Personnes/{firstName}/{lastName}")
                public  Personne udapteApersonne(@RequestBody Personne personne) {

            personneRepository.udapteInformationOfaPersonne(personne);

            return personne;
    }

   @GetMapping("/communityEmail")
    public List<String> emailAssociatedToACity(@RequestParam final String city) {

        return personneService.getEmailOfPersonneByCity(city);

    }

    @GetMapping("/childAlert")
    public ChildrenDtos childrenListAtOneAdress(@RequestParam final String address) {

            return safetyAlertService.getChildrenAndPersonnesAssociatedToAChildByAdress(address); }


}



