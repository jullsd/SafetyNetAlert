package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.dto.*;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class SafetyAlertServiceTest {

    @Mock
    private FireStationService fireStationService;

    @Mock
    private PersonneService personneService;

    @Mock
    private MedicalRecordService medicalRecordService;

    @Mock
    private AgeCalulatorService ageCalulatorService;

    public static final String FIRE_STATION_ADRESS = "aa";
    public static final int FIRE_STATION_NUMBER_1 = 1;
    public static final String BIRTHDAY = "14/04/1991";
    public static final String FIRST_NAME = "Michel";
    public static final String LAST_NAME = "Dupont";

    public static final String CACAHUETE = "cacahuete";

    public static final String PARACETAMOL ="paracetamole";

    public static final int AGE_OF_A_PERSON = 17;

    public static final  String EMAIL = "ju.i@aa.com";

    public static final String ADDRESS_OF_THE_PERSONNE = "2 rue de la rue";

    public static final String ADDRESS_OF_THE_OTHER_PERSONNE = "2 rue de la rue de l'autre";
    public static final String OTHER_ADRESS = "18 rue de la rue";
    public static final String CITY = "BUC";

    public static final String ZIP = "78530";
    public static final String PHONE = "01323232323";


    @Test
    void getPersonnesByFireStationNumber() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<FireStation> fireStations = new ArrayList<>();
        List<PersonneDto> personneDtos = new ArrayList<>();
        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER_1);
        fireStations.add(fireStation);

        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);

        personneDtos.add(personneDto);

        when(fireStationService.getFireStationsByNumber(FIRE_STATION_NUMBER_1)).thenReturn(fireStations);
        when(personneService.getPersonnesByFireStations(fireStations)).thenReturn(personneDtos);

        List<PersonneDto>  personneDtosresults = safetyAlertService.getPersonnesByFireStationNumber(FIRE_STATION_NUMBER_1);

        assertThat(personneDtosresults.get(0).getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(personneDtosresults.get(0).getZip()).isEqualTo(ZIP);

    }


    @Test
    void getPhoneNumbersByFireStation() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<FireStation> fireStations = new ArrayList<>();
        List<String> phoneNumbers = new ArrayList<>();
        List<String> adresses = new ArrayList<>();

        phoneNumbers.add(PHONE);
        FireStation fireStation = new FireStation(FIRE_STATION_ADRESS, FIRE_STATION_NUMBER_1);
        fireStations.add(fireStation);
        adresses.add(FIRE_STATION_ADRESS);

        when(fireStationService.getFireStationsByNumber(FIRE_STATION_NUMBER_1)).thenReturn(fireStations);
        when(personneService.getPhoneNumbersByAdress(adresses)).thenReturn(phoneNumbers);

        assertThat(safetyAlertService.getPhoneNumbersByFireStation(FIRE_STATION_NUMBER_1)).isEqualTo(phoneNumbers);

    }

    @Test
    void getChildrensbyAdress() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<ChildrenDto> childrenAtOneAdress = new ArrayList<>();
        List<PersonneDto> personneDtos = new ArrayList<>();

        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        ChildrenDto childrenDTO = new ChildrenDto(personneDto.getFirstName(), personneDto.getLastName(),
                ageCalulatorService.ageCalculation(BIRTHDAY));

        personneDtos.add(personneDto);
        childrenAtOneAdress.add(childrenDTO);

        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME, FIRST_NAME)).thenReturn(BIRTHDAY);
        when(ageCalulatorService.isChild(BIRTHDAY)).thenReturn(true);
        when(personneService.getPersonnesByAdress(OTHER_ADRESS)).thenReturn(personneDtos);

        assertThat(safetyAlertService.getChildrensbyAdress(OTHER_ADRESS)).isEqualTo(childrenAtOneAdress);

    }

    @Test
    void dontGetChildrensbyAdress() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<ChildrenDto> childrenAtOneAdress = new ArrayList<>();
        List<PersonneDto> personneDtos = new ArrayList<>();

        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        ChildrenDto childrenDTO = new ChildrenDto(personneDto.getFirstName(), personneDto.getLastName(),
               ageCalulatorService.ageCalculation(BIRTHDAY));

        personneDtos.add(personneDto);
        childrenAtOneAdress.add(childrenDTO);

        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME, FIRST_NAME)).thenReturn(BIRTHDAY);
        when(ageCalulatorService.isChild(BIRTHDAY)).thenReturn(false);
        when(personneService.getPersonnesByAdress(OTHER_ADRESS)).thenReturn(personneDtos);

        assertThat(safetyAlertService.getChildrensbyAdress(OTHER_ADRESS)).isNotEqualTo(childrenAtOneAdress);

    }

    @Test
    void getPersonnesAssociatedToAChildByAdress() {
        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        List<PersonneDto> personneDtos = new ArrayList<>();
        personneDtos.add(personneDto);

        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME, FIRST_NAME)).thenReturn(BIRTHDAY);
        when(ageCalulatorService.isAdult(BIRTHDAY)).thenReturn(true);
        when(personneService.getPersonnesByAdress(OTHER_ADRESS)).thenReturn(personneDtos);

        assertThat(safetyAlertService.getPersonnesAssociatedToAChildByAdress(OTHER_ADRESS)).isEqualTo(personneDtos);
    }

    @Test
    void dontGetPersonnesAssociatedToAChildByAdress() {
        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        List<PersonneDto> personneDtos = new ArrayList<>();
        personneDtos.add(personneDto);

        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME, FIRST_NAME)).thenReturn(BIRTHDAY);
        when(ageCalulatorService.isAdult(BIRTHDAY)).thenReturn(false);
        when(personneService.getPersonnesByAdress(OTHER_ADRESS)).thenReturn(personneDtos);

        assertThat(safetyAlertService.getPersonnesAssociatedToAChildByAdress(OTHER_ADRESS)).isNotEqualTo(personneDtos);
    }



    @Test
    void getAdultCountPlusByPersonnes() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<PersonneDto> personneDtos = new ArrayList<>();
        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        personneDtos.add(personneDto);

        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME, FIRST_NAME)).thenReturn(BIRTHDAY);
        when(ageCalulatorService.isAdult(BIRTHDAY)).thenReturn(true);

        assertThat(safetyAlertService.getAdultCountByPersonnes(personneDtos)).isEqualTo(1);
    }

    @Test
    void getAdultCountByPersonnes() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<PersonneDto> personneDtos = new ArrayList<>();
        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        personneDtos.add(personneDto);

        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME, FIRST_NAME)).thenReturn(BIRTHDAY);
        when(ageCalulatorService.isAdult(BIRTHDAY)).thenReturn(false);

        assertThat(safetyAlertService.getAdultCountByPersonnes(personneDtos)).isEqualTo(0);
    }

    @Test
    void getChildrenCountPlusByPersonnes() {
        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<PersonneDto> personneDtos = new ArrayList<>();
        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        personneDtos.add(personneDto);

        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME, FIRST_NAME)).thenReturn(BIRTHDAY);
        when(ageCalulatorService.isChild(BIRTHDAY)).thenReturn(true);

        assertThat(safetyAlertService.getChildrenCountByPersonnes(personneDtos)).isEqualTo(1);
    }


    @Test
    void getChildrenCountByPersonnes() {
        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<PersonneDto> personneDtos = new ArrayList<>();
        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        personneDtos.add(personneDto);

        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME, FIRST_NAME)).thenReturn(BIRTHDAY);
        when(ageCalulatorService.isChild(BIRTHDAY)).thenReturn(false);

        assertThat(safetyAlertService.getChildrenCountByPersonnes(personneDtos)).isEqualTo(0);
    }
    @Test
    void getPersonWithMedicalRecordByAdress() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<PersonneDto> personnes =  new ArrayList<>();
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        medications.add(PARACETAMOL);
        allergies.add(CACAHUETE);


        PersonneDto personneDto = new PersonneDto(FIRST_NAME, LAST_NAME, ADDRESS_OF_THE_PERSONNE, PHONE, ZIP, CITY);
        personnes.add(personneDto);


        when(medicalRecordService.getBirthdayByLastNameAndFirstName(personneDto.getLastName(),personneDto.getFirstName())).thenReturn(BIRTHDAY);
        when(medicalRecordService.getAllergiesByLastNameAndFirstName(personneDto.getLastName(),personneDto.getFirstName())).thenReturn(allergies);
        when(medicalRecordService.getMedicationsByLastNameAndFirstName(personneDto.getLastName(),personneDto.getFirstName())).
                thenReturn(medications);
        when(ageCalulatorService.ageCalculation(BIRTHDAY)).thenReturn(AGE_OF_A_PERSON);
        when(personneService.getPersonnesByAdress(ADDRESS_OF_THE_PERSONNE)).thenReturn(personnes);

        List<PersonWithMedicalRecordDto> personsWithMedicalRecordDtoresults =
                safetyAlertService.getPersonWithMedicalRecordByAdress(ADDRESS_OF_THE_PERSONNE);

        assertThat(personsWithMedicalRecordDtoresults.get(0).getMedications()).isEqualTo(medications);


    }


    @Test
    void getPersonInfoWithMedicalRecordByFirstNameAndLastName() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);
        List<Personne> personnes = new ArrayList<>();

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        medications.add(PARACETAMOL);
        allergies.add(CACAHUETE);

        when(personneService.findAllPersoneByLastNameAndFirstName(FIRST_NAME,LAST_NAME)).thenReturn(personnes);
        when(medicalRecordService.getAllergiesByLastNameAndFirstName(LAST_NAME,FIRST_NAME)).thenReturn(allergies);
        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME,FIRST_NAME)).thenReturn(BIRTHDAY);
        when(medicalRecordService.getMedicationsByLastNameAndFirstName(LAST_NAME,FIRST_NAME)).thenReturn(medications);

        List<PersonInfoWithMedicalRecordDTO> personInfoWithMedicalRecordDTO = safetyAlertService.getPersonInfoWithMedicalRecordByFirstNameAndLastName(FIRST_NAME,LAST_NAME);

        assertThat(personInfoWithMedicalRecordDTO.get(0).getMedications()).isEqualTo(medications);
        assertThat(personInfoWithMedicalRecordDTO.get(0).getAllergies()).isEqualTo(allergies);
    }

    @Test
    void getPersonInfoWith2PersoneWithSameNameMedicalRecordByFirstNameAndLastName() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);
        List<Personne> personnes = new ArrayList<>();

        Personne personne = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        Personne personne2 = new Personne(FIRST_NAME,LAST_NAME,ADDRESS_OF_THE_OTHER_PERSONNE,CITY,ZIP,PHONE,EMAIL);
        personnes.add(personne);
        personnes.add(personne2);
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        medications.add(PARACETAMOL);
        allergies.add(CACAHUETE);

        when(personneService.findAllPersoneByLastNameAndFirstName(FIRST_NAME,LAST_NAME)).thenReturn(personnes);
        when(medicalRecordService.getAllergiesByLastNameAndFirstName(LAST_NAME,FIRST_NAME)).thenReturn(allergies);
        when(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME,FIRST_NAME)).thenReturn(BIRTHDAY);
        when(medicalRecordService.getMedicationsByLastNameAndFirstName(LAST_NAME,FIRST_NAME)).thenReturn(medications);

        List<PersonInfoWithMedicalRecordDTO> personInfoWithMedicalRecordDTO = safetyAlertService.getPersonInfoWithMedicalRecordByFirstNameAndLastName(FIRST_NAME,LAST_NAME);

        assertThat(personInfoWithMedicalRecordDTO.get(0).getAddress()).isEqualTo(ADDRESS_OF_THE_PERSONNE);
        assertThat(personInfoWithMedicalRecordDTO.get(1).getAddress()).isEqualTo(ADDRESS_OF_THE_OTHER_PERSONNE);
    }

    @Test
    void getPersonsAtOneAddressWithMedicalRecordByFireStationsNumbers() {

        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<PerssonesAtOneAdresseDto> personsAtOneAdresse = new ArrayList<>();
        List<PersonWithMedicalRecordDto> personWithMedicalRecordDtos = new ArrayList<>();

        List<String> addresses = new ArrayList<>();
        List<Integer> stations = new ArrayList<>();
        stations.add(FIRE_STATION_NUMBER_1);
        addresses.add(ADDRESS_OF_THE_PERSONNE);

        when(fireStationService.getAllAdressAssociatedToFireStations(stations)).thenReturn(addresses);
        when(safetyAlertService.getPersonWithMedicalRecordByAdress(ADDRESS_OF_THE_PERSONNE)).thenReturn(personWithMedicalRecordDtos);

        PerssonesAtOneAdresseDto perssonesAtOneAdresseDto = new PerssonesAtOneAdresseDto(ADDRESS_OF_THE_PERSONNE,
                safetyAlertService.getPersonWithMedicalRecordByAdress(ADDRESS_OF_THE_PERSONNE));



        personsAtOneAdresse = safetyAlertService.getPersonsAtOneAddressWithMedicalRecordByFireStationsNumbers(stations);

        assertThat(personsAtOneAdresse.get(0).getPersonsWithMedicalRecordDtos()).isEqualTo(personWithMedicalRecordDtos);

    }
}