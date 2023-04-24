package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.dto.ChildrenDto;
import com.SafetyNett.SafetyNetAlert.dto.ChildrenDtos;
import com.SafetyNett.SafetyNetAlert.dto.PersonneDto;
import com.SafetyNett.SafetyNetAlert.dto.PersonneDtos;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class SafetyNetAlertControllerTest {
    @Autowired
    public MockMvc mockMvc;


    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getPerssonesDtosAssociatedToAFireStation() throws Exception {
        PersonneDto personneDto = new PersonneDto(SafetyAlertServiceTest.FIRST_NAME, SafetyAlertServiceTest.LAST_NAME, SafetyAlertServiceTest.ADDRESS_OF_THE_PERSONNE,
                SafetyAlertServiceTest.PHONE, SafetyAlertServiceTest.ZIP, SafetyAlertServiceTest.CITY);
        List<PersonneDto> personneDtos = new ArrayList<>();
        personneDtos.add(personneDto);

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/firestation")
                        .queryParam("fireStationNumber", String.valueOf(1))
                        .content(asJsonString(new PersonneDtos(personneDtos,0,1)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }


    @Test
    void getChildrenListAtOneAdress() throws Exception {

        String result = "{\"childrenDtos\":[{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"birthDate\":\"02/18/2012\"},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"birthDate\":\"09/06/2017\"}],\"perssonesAssociatedToAChild\":[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"phone\":\"841-874-6512\",\"zip\":\"97451\",\"city\":\"Culver\"},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"phone\":\"841-874-6513\",\"zip\":\"97451\",\"city\":\"Culver\"},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"phone\":\"841-874-6544\",\"zip\":\"97451\",\"city\":\"Culver\"}]}";

      MvcResult mvcResult =  mockMvc.perform( MockMvcRequestBuilders
                        .get("/childAlert")
                        .queryParam("address", "1509 Culver St")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.childrenDtos[0].firstName").value("Tenley"))
              .andReturn();

        assertThat(result).isEqualTo(mvcResult.getResponse().getContentAsString());

    }


    @Test
    void getPhoneNumbersAssociatedToAFireStation() throws Exception {

        List<String> phoneNumbers = new ArrayList<>();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/phoneAlert")
                        .queryParam("firestation_number", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk());




    }


}