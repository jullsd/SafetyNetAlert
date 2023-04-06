package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.dto.ChildrenDto;
import com.SafetyNett.SafetyNetAlert.dto.ChildrenDtos;
import com.SafetyNett.SafetyNetAlert.dto.PersonneDto;
import com.SafetyNett.SafetyNetAlert.dto.PersonneDtos;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doCallRealMethod;
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

    public static final String ADDRESS_OF_THE_PERSONNE = "2 rue de la rue";

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

        assertThat(safetyAlertService.getPersonnesByFireStationNumber(FIRE_STATION_NUMBER_1)).isEqualTo(personneDtos);


    }

    @Test
    void getPersonnesByFireStation() {
        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        List<PersonneDto> personnes = safetyAlertService.getPersonnesByFireStationNumber(FIRE_STATION_NUMBER_1);
        int adultCount = safetyAlertService.getAdultCountByPersonnes(personnes);
        int childrenCount = safetyAlertService.getChildrenCountByPersonnes(personnes);

        PersonneDtos resultat = new PersonneDtos(personnes, adultCount, childrenCount);

        assertThat(safetyAlertService.getPersonnesByFireStation(FIRE_STATION_NUMBER_1)).isEqualTo(resultat);


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
                BIRTHDAY);

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
                BIRTHDAY);

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
    void getChildrenAndPersonnesAssociatedToAChildByAdress() {
        SafetyAlertService safetyAlertService = new SafetyAlertService(fireStationService, personneService, medicalRecordService,
                ageCalulatorService);

        ChildrenDtos childrenDtos = new ChildrenDtos(safetyAlertService.getChildrensbyAdress(ADDRESS_OF_THE_PERSONNE),
                safetyAlertService.getPersonnesAssociatedToAChildByAdress(ADDRESS_OF_THE_PERSONNE));



        assertThat(safetyAlertService.getChildrenAndPersonnesAssociatedToAChildByAdress(ADDRESS_OF_THE_PERSONNE)).isEqualTo(childrenDtos);
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
}