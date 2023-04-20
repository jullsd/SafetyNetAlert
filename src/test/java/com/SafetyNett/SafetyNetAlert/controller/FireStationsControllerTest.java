package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class FireStationsControllerTest {

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
    void addAFireStation() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/firestation")
                        .param("fireStation",String.valueOf(new FireStation("Alle des fruits",1)))
                        .content(asJsonString(new FireStation("Alle des fruits",1)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    void deleteAFireStation() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                    .delete("/firestation")
                    .param("fireStation",String.valueOf(new FireStation("Alle des fruits",1)))
                    .content(asJsonString(new FireStation("Alle des fruits",1)))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    void udapteAFireStation() throws Exception {


    }
}