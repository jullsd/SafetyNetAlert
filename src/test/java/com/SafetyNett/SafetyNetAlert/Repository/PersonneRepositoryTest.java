package com.SafetyNett.SafetyNetAlert.Repository;


import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepositoryDataMemory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PersonneRepositoryTest {

    @Mock
    private static DataReaderFromAJson jsonReaderRepository;
    private List<Personne> personneInData = new ArrayList<>();

    private  Personne PERSONNE = new Personne("Sayd","Julien","11","Buc","7832","0632323525","julien.sayd@gmail.com");


    @Test
    public void AddANewPersonne() {

        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);

        personneRepository.addNewPersonne(PERSONNE);

        List personnes = personneRepository.findAll();

        assertThat(personnes).contains(PERSONNE);
    }

    @Test
    public void DeleteAPersonne() {

        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);
        personneRepository.addNewPersonne(PERSONNE);
        List personnes = personneRepository.deleteAPersonne(PERSONNE);

        List personnesInData = personneRepository.findAll();

        assertThat(personnesInData).doesNotContain(PERSONNE);
    }
    @Test
   public void findAPersonneByLastNAmeAndFirstNametest() {

        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);

        personneRepository.addNewPersonne(PERSONNE);



       Personne personne = personneRepository.findByLastNameAndFirstName("Julien","Sayd");

       assertThat(personne).isEqualTo(PERSONNE);

    }

    @Test
    public void DontfindAPersonneByFirstNametest() {
        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);
        personneRepository.addNewPersonne(PERSONNE);

        Personne personne = personneRepository.findByLastNameAndFirstName("aa","Sayd");

        assertThat(personne).isNull();
    }
    @Test
    public void DontfindAPersonneByLastName() {
        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);
        personneRepository.addNewPersonne(PERSONNE);

        Personne personne = personneRepository.findByLastNameAndFirstName("Julien","aa");

        assertThat(personne).isNull();
    }
    @Test
    public void udapteInformationOfAPersonneTest() {
        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);
        personneRepository.addNewPersonne(PERSONNE);

        PERSONNE.setPhone("2222");
        PERSONNE.setCity("asa");

        personneRepository.udapteInformationOfaPersonne(PERSONNE);

       assertThat(PERSONNE.getPhone()).isEqualTo("2222");
        assertThat(PERSONNE.getCity()).isEqualTo("asa");

    }



}
