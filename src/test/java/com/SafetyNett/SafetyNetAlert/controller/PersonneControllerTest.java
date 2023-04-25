package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepository;
import com.SafetyNett.SafetyNetAlert.repository.PersonneRepository;
import com.SafetyNett.SafetyNetAlert.service.PersonneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest( controllers = PersonneController.class )
class PersonneControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private PersonneRepository personneRepository;

    @MockBean
    private PersonneService personneService;
    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final Personne PERSONNE = new Personne("Sayd","Julien","11","Buc","7832","0632323525","julien.sayd@gmail.com");
    private final Personne PERSONNEMODIFIED = new Personne("Michel","Julien","11","Buc","7832","0632323525","julien.sayd@gmail.com");
    @Test
    void addANewPersonne() throws Exception {
        when(personneRepository.addNewPersonne(PERSONNE)).thenReturn(PERSONNE);

        mockMvc.perform( MockMvcRequestBuilders
                    .post("/person")
                    .content(asJsonString(PERSONNE))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sayd"));
    }
    @Test
    void deleteAPersonne() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                    .delete("/person")
                        .content(asJsonString(PERSONNE))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void udapteApersonne() throws Exception {

        when(personneRepository.udapteInformationOfaPersonne(PERSONNE)).thenReturn(PERSONNEMODIFIED);
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/person")
                        .content(asJsonString(PERSONNE))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Michel"));
    }


}