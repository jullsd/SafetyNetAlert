package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith( MockitoExtension.class)
public class PersonneServiceTest {

    @Mock
    private static DataReaderFromAJson dataReaderFromAJson;


    private static PersonneRepository personneRepository;



    private List<Personne> personneInData = new ArrayList<>();

    private Personne PERSONNE = new Personne("Sayd","Julien","11","Buc","7832","0632323525","julien.sayd@gmail.com");
    Personne personne = new Personne("Sayda","Julien","11","Buc","7832","0632323525","julien.sayd@gmail.com");

/*
    @Test
    public void testGetAllEmailOfACity() {

        PersonneService personneService = new PersonneService();

        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(dataReaderFromAJson);

       List<Personne> allpersonnes = personneRepository.findAll();

        allpersonnes.add(personne);

        personneService.getEmailByCity("Buc");

        assertThat(personneService.getEmailByCity("Buc").contains("julien.sayd@gmail.com"));

    } */

}
