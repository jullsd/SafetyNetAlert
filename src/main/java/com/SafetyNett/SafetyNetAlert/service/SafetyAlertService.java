package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.dto.*;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SafetyAlertService {

    private final FireStationService fireStationService;

    private final PersonneService personneService;

    private final MedicalRecordService medicalRecordService;

    private final AgeCalulatorService ageCalulatorService;

    @Autowired
    public SafetyAlertService(FireStationService fireStationService, PersonneService personneService, MedicalRecordService medicalRecordService, AgeCalulatorService ageCalulatorService) {
        this.fireStationService = fireStationService;
        this.personneService = personneService;
        this.medicalRecordService = medicalRecordService;
        this.ageCalulatorService = ageCalulatorService;
    }


    public List<PersonneDto> getPersonnesByFireStationNumber(int stationNumber) {


        List<FireStation> fireStations = fireStationService.getFireStationsByNumber(stationNumber);
        List<PersonneDto> personneDtos = personneService.getPersonnesByFireStations(fireStations);
        return  personneDtos;

    }

    public int getAdultCountByPersonnes(List<PersonneDto> personnes) {
        int adultCount = 0;
        for (PersonneDto personnesDto : personnes) {
            String birthday = medicalRecordService
                    .getBirthdayByLastNameAndFirstName(personnesDto.getLastName(), personnesDto.getFirstName());
            if(ageCalulatorService.isAdult(birthday)){
                adultCount++;
            }

        }
        return adultCount;
    }

    public int getChildrenCountByPersonnes(List<PersonneDto> personnes) {
        int childrenCount = 0;
        for (PersonneDto personnesDto : personnes) {
            String birthday = medicalRecordService
                    .getBirthdayByLastNameAndFirstName(personnesDto.getLastName(), personnesDto.getFirstName());
            if(ageCalulatorService.isChild(birthday)){
                childrenCount++;
            }

        }
        return childrenCount;
    }


    public PersonneDtos getPersonnesByFireStation(int stationNumber) {
       List<PersonneDto> personnes = getPersonnesByFireStationNumber(stationNumber);
       int adultCount = getAdultCountByPersonnes(personnes);
       int childrenCount = getChildrenCountByPersonnes(personnes);

       PersonneDtos  resultat = new PersonneDtos(personnes, adultCount, childrenCount);

       return resultat;

    }

    public List<String> getPhoneNumbersByFireStation(int fireStation) {

        List<String> adresses = new ArrayList<>();

        List<FireStation> fireStations = fireStationService.getFireStationsByNumber(fireStation);
        for(FireStation firestation : fireStations){
           String adress = firestation.getAddress();
           adresses.add(adress);
        }

        List<String> phoneNumbers = personneService.getPhoneNumbersByAdress(adresses);


        return phoneNumbers;
    }

    public List<PersonWithMedicalRecordDto> getPersonWithMedicalRecordByAdress(String address) {

        List<PersonneDto> personnes = personneService.getPersonnesByAdress(address);
        List<PersonWithMedicalRecordDto> personsWithMedicalRecordDto = new ArrayList<>();


        for(PersonneDto personneDto: personnes) {
            String birthday = medicalRecordService.getBirthdayByLastNameAndFirstName(personneDto.getLastName(),personneDto.getFirstName());
            List<String> allergies = medicalRecordService.getAllergiesByLastNameAndFirstName(personneDto.getLastName(),personneDto.getFirstName());
            List<String> medications = medicalRecordService.getMedicationsByLastNameAndFirstName(personneDto.getLastName(),personneDto.getFirstName());
            int ageOfPersoneDto = ageCalulatorService.ageCalculation(birthday);

           PersonWithMedicalRecordDto personWithMedicalRecordDto = new PersonWithMedicalRecordDto(personneDto.getLastName(),personneDto.getPhone(),ageOfPersoneDto
           ,medications,allergies);

            personsWithMedicalRecordDto.add(personWithMedicalRecordDto);

        }
        return personsWithMedicalRecordDto;

    }

    public PersonWithMedicalRecordDtos getPersonsWithMedicalRecordAndFireNumberByAdress(String address) {

        PersonWithMedicalRecordDtos personsWithMedicalRecordDtos = new PersonWithMedicalRecordDtos(
                fireStationService.getFireStationsNumberByAdress(address),getPersonWithMedicalRecordByAdress(address));

        return  personsWithMedicalRecordDtos;


    }

    public PersonInfoWithMedicalRecordDTO getPersonInfoWithMedicalRecordByFirstNameAndLastName(String firstName, String lastName) {

        Personne personne = personneService.findByLastNameAndFirstName(firstName,lastName);
        String birthday = medicalRecordService.getBirthdayByLastNameAndFirstName(lastName,firstName);
        List<String> allergies = medicalRecordService.getAllergiesByLastNameAndFirstName(lastName,firstName);
        List<String> medications = medicalRecordService.getMedicationsByLastNameAndFirstName(lastName,firstName);

        PersonInfoWithMedicalRecordDTO personInfoWithMedicalRecordDTO = new PersonInfoWithMedicalRecordDTO(personne.getLastName(),
                personne.getAddress(), ageCalulatorService.ageCalculation(birthday), personne.getEmail(),medications,allergies);

          return personInfoWithMedicalRecordDTO;


    }

    public List<PerssonesAtOneAdresseDto> getPersonsAtOneAddressWithMedicalRecordByFireStationsNumbers(List<Integer> stations)  {

        List<PerssonesAtOneAdresseDto> personsAtOneAdresse = new ArrayList<>();
        List<String> addresses = fireStationService.getAllAdressAssociatedToFireStations(stations);

        for(String address : addresses) {
        PerssonesAtOneAdresseDto perssonesAtOneAdresseDto = new PerssonesAtOneAdresseDto(address,getPersonWithMedicalRecordByAdress(address));
            personsAtOneAdresse.add(perssonesAtOneAdresseDto);
        }

        return  personsAtOneAdresse;
    }



    public List<ChildrenDto> getChildrensbyAdress(String address) {

        List<PersonneDto> personnes = personneService.getPersonnesByAdress(address);
        List<ChildrenDto> childrenAtOneAdress = new ArrayList<>();
        for(PersonneDto personneDto: personnes) {
           String birthday = medicalRecordService.getBirthdayByLastNameAndFirstName(personneDto.getLastName(),personneDto.getFirstName());
           if(ageCalulatorService.isChild(birthday)) {
               ChildrenDto childrenDTO = new ChildrenDto(personneDto.getFirstName(), personneDto.getLastName(),
                       birthday);

               childrenAtOneAdress.add(childrenDTO);
           }

        }
        return  childrenAtOneAdress;
    }

    public List<PersonneDto> getPersonnesAssociatedToAChildByAdress(String address) {

        List<PersonneDto> personnesAssociatedToAChild = new ArrayList<>();
        List<PersonneDto> personnes = personneService.getPersonnesByAdress(address);

        for(PersonneDto personneDto: personnes) {
            String birthday = medicalRecordService.getBirthdayByLastNameAndFirstName(personneDto.getLastName(),personneDto.getFirstName());
            if(ageCalulatorService.isAdult(birthday)) {
                personnesAssociatedToAChild.add(personneDto);}

            }
           return personnesAssociatedToAChild;
    }

    public ChildrenDtos getChildrenAndPersonnesAssociatedToAChildByAdress(String address) throws Exception {

        ChildrenDtos result = new ChildrenDtos(getChildrensbyAdress(address),getPersonnesAssociatedToAChildByAdress(address));

        return result;
    }
}

