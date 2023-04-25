package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.dto.*;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertService;
import com.SafetyNett.SafetyNetAlert.service.SafetyAlertServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class SafetyNetAlertControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @Test
    void getPerssonesDtosAssociatedToAFireStation() throws Exception {


        mockMvc.perform( MockMvcRequestBuilders
                        .get("/firestation")
                        .queryParam("stationNumber", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personnes[0].firstName").value("Peter"))
                .andExpect(jsonPath("$.adultCount").value("5"));
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


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/phoneAlert")
                        .queryParam("firestation", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").value("841-874-6512"));
    }

    @Test
    void getPerssonsWithMedicalAssociatedToAFireStation() throws Exception {

        String result = "{\"fireStationNumber\":2,\"personsWithMedicalRecordDtos\":[{\"lastName\":\"Cadigan\",\"phone\":\"841-874-7458\",\"age\":77,\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[]}]}";

        MvcResult mvcResult =  mockMvc.perform( MockMvcRequestBuilders

                        .get("/fire")
                        .queryParam("address", "951 LoneTree Rd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fireStationNumber").value(2))
                .andReturn();

        assertThat(result).isEqualTo(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getPersonnesAtOneAdressesAssociatedtoFiresStations() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/flood")
                        .queryParam("stations", String.valueOf(1))
                        .queryParam("stations", String.valueOf(2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].address").value("29 15th St"));

    }


    @Test
    void getPersonInfoWithMedicalRecordByFirstNameAndLastName()  throws Exception{

        String result ="{\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"age\":39,\"email\":\"jaboyd@email.com\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}";

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders

                .get("/personInfo")
                                .queryParam("firstName","John")
                                .queryParam("lastName","Boyd")
        .contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
                        .andExpect(jsonPath("$.age").value(39))
                .andReturn();

        assertThat(result).isEqualTo(mvcResult.getResponse().getContentAsString());

    }
}