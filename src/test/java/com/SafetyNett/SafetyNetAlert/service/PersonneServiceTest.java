package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.dto.PersonneDto;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepositoryDataMemory;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepositoryDataMemory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class)
class PersonneServiceTest {

    @Mock
    private PersonneRepositoryDataMemory personneRepository;

    private final String FIRE_STATION_ADRESS = "2 rue de la rue";
    private final int FIRE_STATION_NUMBER = 1;

    private final String FIRST_NAME = "Michel";
    private final String LAST_NAME = "Dupont";
    private final String ADDRESS_OF_FIRE_STATION_ADRESS = "2 rue de la rue";

    private final String ADDRESS_OF_THE_PERSONNE = "2 rue de la rue";

    private final String OTHER_ADRESS = "18 rue de la rue";
    private final String CITY = "BUC";

    private final String OTHER_CITY = "PARIS";
    private final String ZIP = "78530";
    private final String PHONE = "01323232323";
    private final String EMAIL = "ju.i@aa.com";


    @Test
    void getPersonnesByFireStations() {

        PersonneService personneService = new PersonneService(personneRepository);

        List<FireStation> fireStations =  new ArrayList<>();
        List<Personne> personnes =  new ArrayList<>();
        List<PersonneDto> personneDtos =  new ArrayList<>();

        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER);
        fireStations.add(fireStation);

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_FIRE_STATION_ADRESS,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        PersonneDto personneDto = new PersonneDto(FIRST_NAME,LAST_NAME,ADDRESS_OF_FIRE_STATION_ADRESS,PHONE,ZIP,CITY);
        personneDtos.add(personneDto);


        when(personneRepository.findAll()).thenReturn(personnes);


        assertThat(personneService.getPersonnesByFireStations(fireStations)).isEqualTo(personneDtos);

    }

    @Test
    void dontGetAPersonnesByFireStations() {

        PersonneService personneService = new PersonneService(personneRepository);

        List<FireStation> fireStations =  new ArrayList<>();
        List<Personne> personnes =  new ArrayList<>();
        List<PersonneDto> personneDtos =  new ArrayList<>();

        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER);
        fireStations.add(fireStation);

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,OTHER_ADRESS,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        PersonneDto personneDto = new PersonneDto(FIRST_NAME,LAST_NAME,ADDRESS_OF_FIRE_STATION_ADRESS,PHONE,ZIP,CITY);
        personneDtos.add(personneDto);


        when(personneRepository.findAll()).thenReturn(personnes);


        assertThat(personneService.getPersonnesByFireStations(fireStations)).doesNotContain(personneDto);




    }



    @Test
    void getPersonnesByAdress() {

        PersonneService personneService = new PersonneService(personneRepository);

        List<Personne> personnes =  new ArrayList<>();
        List<PersonneDto> personneDtos =  new ArrayList<>();

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        PersonneDto personneDto = new PersonneDto(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,PHONE,ZIP,CITY);
        personneDtos.add(personneDto);

        when(personneRepository.findAll()).thenReturn(personnes);

        assertThat(personneService.getPersonnesByAdress(ADDRESS_OF_THE_PERSONNE)).isEqualTo(personneDtos);

    }

    @Test
    void dontGetPersonnesByAdress() {

        PersonneService personneService = new PersonneService(personneRepository);

        List<Personne> personnes =  new ArrayList<>();
        List<PersonneDto> personneDtos =  new ArrayList<>();

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        PersonneDto personneDto = new PersonneDto(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,PHONE,ZIP,CITY);
        personneDtos.add(personneDto);

        when(personneRepository.findAll()).thenReturn(personnes);

        assertThat(personneService.getPersonnesByAdress(OTHER_ADRESS)).doesNotContain(personneDto);

    }


    @Test
    void getPhoneNumbersByAdress() {
        PersonneService personneService = new PersonneService(personneRepository);

        List<String> adresses = new ArrayList<>();
        List<Personne> personnes =  new ArrayList<>();

        adresses.add(ADDRESS_OF_THE_PERSONNE);

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);


        when(personneRepository.findAll()).thenReturn(personnes);

        assertThat(personneService.getPhoneNumbersByAdress(adresses)).contains(PHONE);

    }

    @Test
    void dontGetPhoneNumbersByAdress() {
        PersonneService personneService = new PersonneService(personneRepository);

        List<String> adresses = new ArrayList<>();
        List<Personne> personnes =  new ArrayList<>();

        adresses.add(OTHER_ADRESS);

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);


        when(personneRepository.findAll()).thenReturn(personnes);

        assertThat(personneService.getPhoneNumbersByAdress(adresses)).doesNotContain(PHONE);
    }

    @Test
    void getEmailOfPersonneByCity() {

        PersonneService personneService = new PersonneService(personneRepository);


        List<Personne> personnes =  new ArrayList<>();


        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        when(personneRepository.findAll()).thenReturn(personnes);


        assertThat(personneService.getEmailOfPersonneByCity(CITY)).contains(EMAIL);


    }

    @Test
    void dontGetEmailOfPersonneByCity() {

        PersonneService personneService = new PersonneService(personneRepository);


        List<Personne> personnes =  new ArrayList<>();


        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        when(personneRepository.findAll()).thenReturn(personnes);


        assertThat(personneService.getEmailOfPersonneByCity(OTHER_CITY).contains(EMAIL));


    }

    @Test
    void findByLastNameAndFirstName() {

        List<Personne> personnes =  new ArrayList<>();

        PersonneService personneService = new PersonneService(personneRepository);

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        when(personneRepository.findAll()).thenReturn(personnes);

        assertThat(personneService.findByLastNameAndFirstName(FIRST_NAME,LAST_NAME)).isEqualTo(personne);

    }
    @Test
    void dontFindByLastName() {

        List<Personne> personnes =  new ArrayList<>();

        PersonneService personneService = new PersonneService(personneRepository);

        Personne personne = new Personne(FIRST_NAME,"Saya",ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        when(personneRepository.findAll()).thenReturn(personnes);

        assertThat(personneService.findByLastNameAndFirstName(FIRST_NAME,LAST_NAME)).isNull();

    }

    @Test
    void dontFindByFirstName() {

        List<Personne> personnes =  new ArrayList<>();

        PersonneService personneService = new PersonneService(personneRepository);

        Personne personne = new Personne("aa",LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);

        when(personneRepository.findAll()).thenReturn(personnes);

        assertThat(personneService.findByLastNameAndFirstName(FIRST_NAME,LAST_NAME)).isNull();

    }

}