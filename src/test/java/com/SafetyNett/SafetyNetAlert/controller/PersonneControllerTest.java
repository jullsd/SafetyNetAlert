package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonneControllerTest {

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
    void addANewPersonne() throws Exception {mockMvc.perform( MockMvcRequestBuilders
                    .post("/person")
                    .param("personne",String.valueOf(new Personne("Michel","Dupont","Buc","78530","14/04/1991","aa","")))
                    .content(asJsonString((new Personne("Michel","Dupont","Buc","78530","14/04/1991","aa",""))))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }
   @Disabled
    @Test
    void deleteAPersonne() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                    .delete("/person")
                    .param("personne",String.valueOf(new Personne("Michel","Dupont","Buc","78530","14/04/1991","aa","")))
                    .content(asJsonString(new Personne("Michel","Dupont","Buc","78530","14/04/1991","aa","")))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    void udapteApersonne() {
    }

    @Test
    void emailAssociatedToACity() {
    }
}