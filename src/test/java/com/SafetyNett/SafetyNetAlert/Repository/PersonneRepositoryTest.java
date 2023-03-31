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
    private  Personne personne = new Personne("Sayda","Julien","11","Buc","7832","0632323525","julien.sayd@gmail.com");

    @Test
    public void testGetANewPersonne() {


        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);


        List personnes = personneRepository.findAll();

        personneRepository.addNewPersonne(PERSONNE);

        assertThat(personnes).contains(PERSONNE);
    }

    @Test
    public void testDeleteAPersonne() {

        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);

        personneInData.add(0,PERSONNE);

        List personnes = personneRepository.deleteAPersonne(PERSONNE);

        assertThat(personnes).doesNotContain(PERSONNE);
    }
    @Test
   public void findAPersonneByLastNAmeAndFirstNametest() {
        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);
        List personneInData = personneRepository.findAll();
        personneInData.add(0,PERSONNE);

       Personne personne = personneRepository.findByLastNameAndFirstName("Julien","Sayd");

       assertThat(personne).isEqualTo(PERSONNE);

    }

    @Test
    public void DontfindAPersonneByLastNAmeAndFirstNametest() {
        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);
        List personneInData = personneRepository.findAll();
        personneInData.add(0,PERSONNE);

        Personne personne = personneRepository.findByLastNameAndFirstName("aa","sa");

        assertThat(personne).isNull();
    }

    @Test
    public void udapteInformationOfAPersonneTest() {
        PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(jsonReaderRepository);
        List personneInData = personneRepository.findAll();
        personneInData.add(0,personne);
        personne.setPhone("2222");
        personne.setCity("asa");
       personneRepository.udapteInformationOfaPersonne(personne);

       assertThat(personne.getPhone()).isEqualTo("2222");
        assertThat(personne.getCity()).isEqualTo("asa");

    }



}
