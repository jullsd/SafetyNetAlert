package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.Personne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Slf4j
public class PersonneRepositoryDataMemory implements PersonneRepository {


    private List<Personne> personnes = new ArrayList<>();

    @Autowired
    public PersonneRepositoryDataMemory(DataReaderFromAJson dataReaderFromAJson) {
        personnes.addAll(dataReaderFromAJson.personne());
    }


    @Override
    public List<Personne> findAll() {
        List<Personne> personnesClone = new ArrayList<>(personnes);
        log.debug("Response to findAll(): {}", personnesClone);
        return personnesClone;

    }

    @Override
    public Personne findByLastNameAndFirstName(String lastName, String firstName) {
        log.debug("Call findByLastNameAndFirstName with {}&{}", lastName, firstName);
        for(Personne personne : personnes) {
            if ((personne.getFirstName().equals(firstName)) && (personne.getLastName().equals(lastName))) {
                log.debug("Respone findByLastNameAndFirstName with {} {} : {}", lastName, firstName, personne);
                return personne;
            }

        }
        log.debug("Failed to findByLastNameAndFirstName with {}&{}",lastName,firstName);
        throw new IllegalArgumentException("Unkown lastName or firstName");
    }

    @Override
    public Personne addNewPersonne(Personne personne) {

        personnes.add(personne);

        return personne;
    }

    @Override
    public void deleteAPersonne(Personne personne) {
        personnes = findAll();
        personne = findByLastNameAndFirstName(personne.getLastName(), personne.getFirstName());
        personnes.remove(personne);


    }

    @Override
    public Personne udapteInformationOfaPersonne(Personne personne) {


        personne = findByLastNameAndFirstName(personne.getLastName(), personne.getFirstName());
        int personneIndex = personnes.indexOf(findByLastNameAndFirstName(personne.getLastName(), personne.getFirstName()));
        personnes.set(personneIndex, personne);


        return personne;

    }

}








