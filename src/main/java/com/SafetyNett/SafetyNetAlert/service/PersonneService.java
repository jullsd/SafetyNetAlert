package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.dto.PersonneDto;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonneService {


    private final PersonneRepository personneRepository;

    @Autowired
    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<PersonneDto> getPersonnesByFireStations(List<FireStation> fireStations) {

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
        return personneDtos;
    }

    public List<PersonneDto> getPersonnesByAdress(String address) {

        List<PersonneDto> personneDtos = new ArrayList<>();

        List<Personne> personnes = personneRepository.findAll();

        for(Personne personne : personnes) {
            if ((personne.getAddress()).equals(address)) {
                PersonneDto personneDTO = new PersonneDto(personne.getFirstName(),
                        personne.getLastName(), personne.getAddress(), personne.getPhone(), personne.getZip(), personne.getCity());

                personneDtos.add(personneDTO);
            }
        }

        return personneDtos;
    }

    public Personne findByLastNameAndFirstName(String firstName, String lastName) {

        List<Personne> personnes = personneRepository.findAll();

        for(Personne personne : personnes) {
            if ((personne.getFirstName().equals(firstName)) && (personne.getLastName().equals(lastName))) {
                return personne;
            }
        }

        return null;
    }

    public List<String> getPhoneNumbersByAdress(List<String> adresses) {

        List<String> phoneNumbers = new ArrayList<>();
        List<Personne> allpersonnes = personneRepository.findAll();

        for(Personne personne : allpersonnes) {
            for(String adress : adresses) {
                if ((personne.getAddress()).equals(adress)) {

                    phoneNumbers.add(personne.getPhone());
                }
            }
        }
        return phoneNumbers;
    }

    public List<String> getEmailOfPersonneByCity(String city) {

        List<Personne> allpersonnes = personneRepository.findAll();
        List<String> emails = new ArrayList<>();

        for(Personne personne : allpersonnes) {
            if ((personne.getCity()).equals(city)) emails.add(personne.getEmail());
        }
        return emails;
    }


}













