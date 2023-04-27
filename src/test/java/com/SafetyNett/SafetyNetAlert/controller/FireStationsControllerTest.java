package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest( controllers = FireStationsController.class )
public class FireStationsControllerTest {

    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final FireStation FIRESTATION = new FireStation("adress1", 2);
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private FireStationRepository fireStationRepository;

    @Test
    public void testDeleteFireStation() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
                        .content(asJsonString(FIRESTATION))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addAFireStation() throws Exception {
        when(fireStationRepository.addAFireStation(FIRESTATION)).thenReturn(FIRESTATION);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/firestation")
                        .content(asJsonString(FIRESTATION))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("adress1"));
        ;

    }


    @Test
    public void updateAFireStation() throws Exception {
        when(fireStationRepository.udapteAFireStaion(FIRESTATION)).thenReturn(new FireStation("adress", 1));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/firestation")
                        .content(asJsonString(FIRESTATION))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath(".address").value("adress"));
    }


}

