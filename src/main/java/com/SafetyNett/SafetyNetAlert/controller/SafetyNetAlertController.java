package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.dto.*;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class SafetyNetAlertController {

    SafetyAlertService safetyAlertService;

    @Autowired
    public SafetyNetAlertController(SafetyAlertService safetyAlertService) {
        this.safetyAlertService = safetyAlertService;
    }

    @GetMapping( "/childAlert" )
    public ResponseEntity<ChildrenDtos> getChildrenListAtOneAdress(@RequestParam final String address) {
        log.info("Call of getChildrenListAtOneAdress with adrese : {}", address);
        try {
            ChildrenDtos childrenDtos = safetyAlertService.getChildrenAndPersonnesAssociatedToAChildByAdress(address);
            log.info("Response to getChildrenListAtOneAdress  {}", childrenDtos);
            return new ResponseEntity<>(childrenDtos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to getChildrenListAtOneAdress  with {}", address);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }


    }

    @GetMapping( "/firestation" )
    public ResponseEntity<PersonneDtos> getPerssonesDtosAssociatedToAFireStation(@RequestParam final int stationNumber) {
        log.info("Call of getPersonnesDtosAssociatedToAFireStation with fireStationNumber : {}", stationNumber);
        try {
            PersonneDtos personneDtos = safetyAlertService.getPersonnesByFireStation(stationNumber);
            log.info("Response to getPersonnesDtosAssociatedToAFireStation {}", personneDtos);
            return new ResponseEntity<>(personneDtos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to getChildrenListAtOneAdress  with {}", stationNumber);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }
    }

    @GetMapping( "/phoneAlert" )
    public ResponseEntity<List<String>> getPhoneNumbersAssociatedToAFireStation(@RequestParam final int firestation) {
        log.info("Call of getPhoneNumbersAssociatedToAFireStation with firestation_number : {}", firestation);
        try {
            List<String> phoneNumbers = safetyAlertService.getPhoneNumbersByFireStation(firestation);
            log.info("Response to getPhoneNumbersAssociatedToAFireStation {}", phoneNumbers);
            return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to getPhoneNumbersAssociatedToAFireStation with {}", firestation);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping( "/fire" )
    public ResponseEntity<PersonWithMedicalRecordDtos> getPerssonsWithMedicalAssociatedToAFireStation(@RequestParam final String address) {
        log.info("Call of getPerssonsWithMedicalAssociatedToAFireStation with address : {}", address);
        try {
            PersonWithMedicalRecordDtos personWithMedicalRecordDtos = safetyAlertService.getPersonsWithMedicalRecordAndFireNumberByAdress(address);
            log.info("Response to getPerssonsWithMedicalAssociatedToAFireStation : {}", personWithMedicalRecordDtos);
            return new ResponseEntity<>(personWithMedicalRecordDtos, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to getPerssonsWithMedicalAssociatedToAFireStation with {}", address);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }
    }

    @GetMapping( "/flood" )
    public ResponseEntity<List<PerssonesAtOneAdresseDto>>
    getPersonnesAtOneAdressesAssociatedtoFiresStations(@RequestParam final List<Integer> stations) {
        log.info("Call of getPersonnesAtOneAdressesAssociatedtoFiresStations with address : {}", stations);
        try {
            List<PerssonesAtOneAdresseDto> PerssonesAtOneAdresseDto = safetyAlertService.getPersonsAtOneAddressWithMedicalRecordByFireStationsNumbers(stations);
            log.info("Response to getPersonnesAtOneAdressesAssociatedtoFiresStations: {}", stations);
            return new ResponseEntity<>(PerssonesAtOneAdresseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to getPersonnesAtOneAdressesAssociatedtoFiresStations with {}", stations);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }

    }

    @GetMapping( "/personInfo" )
    public ResponseEntity<PersonInfoWithMedicalRecordDTO> getPersonInfoWithMedicalRecordByFirstNameAndLastName(@RequestParam final String firstName, String lastName) {
        log.info("Call of getPersonInfoWithMedicalRecordByFirstNameAndLastName with address : {} & {}", firstName, lastName);


        try {
            PersonInfoWithMedicalRecordDTO personInfoWithMedicalRecordDTO = safetyAlertService.getPersonInfoWithMedicalRecordByFirstNameAndLastName(firstName, lastName);
            log.info("Response to getPersonInfoWithMedicalRecordByFirstNameAndLastName: {}", personInfoWithMedicalRecordDTO);
            return new ResponseEntity<>(personInfoWithMedicalRecordDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to getPersonInfoWithMedicalRecordByFirstNameAndLastName with {} & {}", firstName, lastName);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }


    }
}
