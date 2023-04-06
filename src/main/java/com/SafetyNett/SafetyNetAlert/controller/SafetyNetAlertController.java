package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.dto.ChildrenDtos;
import com.SafetyNett.SafetyNetAlert.dto.PersonneDtos;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ComponentScan
public class SafetyNetAlertController {
   @Autowired
    SafetyAlertService safetyAlertService;

    @GetMapping("/childAlert")
    public ChildrenDtos getChildrenListAtOneAdress(@RequestParam final String address) {

        return safetyAlertService.getChildrenAndPersonnesAssociatedToAChildByAdress(address); }

    @GetMapping("/firestation")
    public PersonneDtos getPerssonesDtosAssociatedToAFireStation(@RequestParam final int fireStationNumber)   {

        return safetyAlertService.getPersonnesByFireStation(fireStationNumber);

    }
    @GetMapping("/phoneAlert")
    public List<String> getPhoneNumbersAssociatedToAFireStation(@RequestParam final int firestation_number)   {

        return safetyAlertService.getPhoneNumbersByFireStation(firestation_number);
    }

}
