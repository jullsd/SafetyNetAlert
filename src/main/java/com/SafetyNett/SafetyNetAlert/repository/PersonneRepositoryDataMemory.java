package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonneRepositoryDataMemory implements PersonneRepository {

    @Autowired
    private DataReaderFromAJson dataReaderFromAJson;

    private List<Personne> personnes = new ArrayList<>();


    public PersonneRepositoryDataMemory(DataReaderFromAJson dataReaderFromAJson) {
        this.dataReaderFromAJson = dataReaderFromAJson;
        personnes.addAll(dataReaderFromAJson.personne());
    }


    @Override
    public List<Personne> findAll() {
        List<Personne> personnesClone = new ArrayList<>(personnes);
        return personnesClone;

    }

    @Override
    public Personne findByLastNameAndFirstName(String lastName, String firstName) {
        for(Personne personne : personnes) {
            if ((personne.getFirstName().equals(firstName)) && (personne.getLastName().equals(lastName))) {
                return personne;
            }
        }
        return null;
    }

    @Override
    public Personne addNewPersonne(Personne personne) {

        personnes.add(personne);

        return personne;
    }

    @Override
    public List<Personne> deleteAPersonne(Personne personne) {

        findByLastNameAndFirstName(personne.getLastName(), personne.getFirstName());
        personnes.remove(personne);

        return personnes;

    }

    @Override
    public Personne udapteInformationOfaPersonne(Personne personne) {


        findByLastNameAndFirstName(personne.getLastName(), personne.getFirstName());
        int personneIndex = personnes.indexOf(findByLastNameAndFirstName(personne.getLastName(), personne.getFirstName()));
        personnes.set(personneIndex, personne);


        return personne;

    }

}








