package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.dto.*;
import com.SafetyNett.SafetyNetAlert.model.FireStation;
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

    /*

    PersonneRepositoryDataMemory personneRepository = new PersonneRepositoryDataMemory(dataReaderFromAJson);
    MedicalRecordRepositoryDataMemory medicalRecordRepository = new MedicalRecordRepositoryDataMemory(dataReaderFromAJson);
    List<Personne> allpersonnes = personneRepository.findAll();
    List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();


    TotalDTO totalDTO = new TotalDTO();




    private List<Personne> perssonesAssociatedToAChild= new ArrayList<>();





    private List<PersonnWithMedicalRecordDTO> personnesWithMedicalRecordAtOneAdress = new ArrayList<>();

    List<String> adressesAssociatedToAFireStation = new ArrayList<>();



    public int ageCalculation(String birthdate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birthdate, format);
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears();
    }



    public List<String> getListOfAllergiesByPerosnne(Personne personne) {

        for(MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getFirstName().equals(personne.getFirstName()) && (medicalRecord.getLastName()).equals(personne.getLastName()))

             return    medicalRecord.getAllergies();

        }
        return null;
    }

    public List<String> getListOfMedicationsByPerosnne(Personne personne) {

        for(MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getFirstName().equals(personne.getFirstName()) && (medicalRecord.getLastName()).equals(personne.getLastName()))

                return    medicalRecord.getMedications();

        }
        return null;
    }
    public List<Personne> personneAssociatedToAChildI(String address) {

        for(Personne personne : allpersonnes) {
            if ((personne.getAddress()).equals(address)) {
            perssonesAssociatedToAChild.add(personne);

            }
        } return perssonesAssociatedToAChild;
    }
*/

    public List<PersonneDto> getPersonnesByFireStationNumber(int fireStationNumber) {

        //TODO to return FIreStationDto
        List<FireStation> fireStations = fireStationService.getFireStationsByNumber(fireStationNumber);
        List<PersonneDto> personneDtos = personneService.getPersonnesByFireStations(fireStations);
        return  personneDtos;

    }

    private int getAdultCountByPersonnes(List<PersonneDto> personnes) {
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

    private int getChildrenCountByPersonnes(List<PersonneDto> personnes) {
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



    public PersonneDtos getPersonnesByFireStation(int fireStationNumber) {
        //This function should return a list of personnes for 1 fire sation number
       List<PersonneDto> personnes = getPersonnesByFireStationNumber(fireStationNumber);
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

    public ChildrenDtos getChildrenAndPersonnesAssociatedToAChildByAdress(String address) {

        ChildrenDtos result = new ChildrenDtos(getChildrensbyAdress(address),getPersonnesAssociatedToAChildByAdress(address));

        return result;
    }
}

