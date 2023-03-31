package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.model.Personne;

import java.util.List;


public interface PersonneRepository {

    List<Personne> findAll();
    Personne findByLastNameAndFirstName(String lastName, String firstName);
    List<Personne> deleteAPersonne(Personne personne);
    Personne addNewPersonne(Personne personne);

    Personne udapteInformationOfaPersonne(Personne personne);

}