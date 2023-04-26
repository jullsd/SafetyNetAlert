package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.dto.PersonneDto;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class PersonneService {


    private final PersonneRepository personneRepository;

    @Autowired
    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<PersonneDto> getPersonnesByFireStations(List<FireStation> fireStations) {
        log.debug("Call getPersonnesByFireStations with {}",fireStations);

        List<PersonneDto> personneDtos = new ArrayList<>();

        List<Personne> personnes = personneRepository.findAll();

        for(FireStation fireStation : fireStations) {
            for(Personne personne : personnes) {
                if ((personne.getAddress()).equals(fireStation.getAddress())) {
                    PersonneDto personneDTO = new PersonneDto(personne.getFirstName(),
                            personne.getLastName(), personne.getAddress(), personne.getPhone(), personne.getZip(), personne.getCity());

                    personneDtos.add(personneDTO);
                }
            }
        }
        log.debug("Response to getPersonnesByFireStations with {} : {}",fireStations,personneDtos);
        return personneDtos;
    }

    public List<PersonneDto> getPersonnesByAdress(String address) {
        log.debug("Call getPersonnesByAdress with {}",address);
        List<PersonneDto> personneDtos = new ArrayList<>();

        List<Personne> personnes = personneRepository.findAll();

        for(Personne personne : personnes) {
            if ((personne.getAddress()).equals(address)) {
                PersonneDto personneDTO = new PersonneDto(personne.getFirstName(),
                        personne.getLastName(), personne.getAddress(), personne.getPhone(), personne.getZip(), personne.getCity());

                personneDtos.add(personneDTO);
            }
        }
        log.debug("Response to getPersonnesByAdress with {} : {}",address,personneDtos);
        return personneDtos;
    }

    public Personne findByLastNameAndFirstName(String firstName, String lastName) {
        log.debug("Call findByLastNameAndFirstName with {}&{}",lastName,firstName);
        List<Personne> personnes = personneRepository.findAll();

        for(Personne personne : personnes) {
            if ((personne.getFirstName().equals(firstName)) && (personne.getLastName().equals(lastName))) {
                log.debug("Respone findByLastNameAndFirstName  with {}&{} :{} ",lastName,firstName,personne);
                return personne;
            }
            log.debug("Failed to findByLastNameAndFirstName with {}&{}",lastName,firstName);
        }

        return null;
    }

    public List<Personne> findAllPersoneByLastNameAndFirstName(String firstName, String lastName) {
        log.debug("Call findAllPersoneByLastNameAndFirstName with {}&{}",lastName,firstName);
        List<Personne> personnes = personneRepository.findAll();
        List<Personne> allpersonnesWithFirstNameAndLastName = new ArrayList<>();

        for(Personne personne : personnes) {
            if ((personne.getFirstName().equals(firstName)) && (personne.getLastName().equals(lastName))) {
                allpersonnesWithFirstNameAndLastName.add(personne);
            }
            log.debug("Failed to findAllPersoneByLastNameAndFirstNamee with {}&{}",lastName,firstName);
        }
        log.debug("Respone findAllPersoneByLastNameAndFirstNamee with {}&{} :{} ",lastName,firstName, allpersonnesWithFirstNameAndLastName);
        return allpersonnesWithFirstNameAndLastName;
    }

    public List<String> getPhoneNumbersByAdress(List<String> adresses) {
        log.debug("Call getPhoneNumbersByAdresswith {}",adresses);

        List<String> phoneNumbers = new ArrayList<>();
        List<Personne> allpersonnes = personneRepository.findAll();

        for(Personne personne : allpersonnes) {
            for(String adress : adresses) {
                if ((personne.getAddress()).equals(adress)) {
                    phoneNumbers.add(personne.getPhone());}
            }
        }
        log.debug("Response to getPhoneNumbersByAdress with {} : {}",adresses,phoneNumbers);
        return phoneNumbers;
    }

    public List<String> getEmailOfPersonneByCity(String city) {
        log.debug("Call getEmailOfPersonneByCity with {}",city);
        List<Personne> allpersonnes = personneRepository.findAll();
        List<String> emails = new ArrayList<>();

        for(Personne personne : allpersonnes) {
            if ((personne.getCity()).equals(city)) emails.add(personne.getEmail());
        }
        log.debug("Response to getEmailOfPersonneByCity with {} : {}",city,emails);
        return emails;
    }


}













