package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepository;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepository;
import com.SafetyNett.SafetyNetAlert.service.MedicalRecordServiceTest;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( controllers = MedicalRecordsController.class )
class MedicalRecordsControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private MedicalRecordRepository medicalRecordRepository;

    private final List<String> allergies = new ArrayList<>();
    private final List<String> medications = new ArrayList<>();

    private final MedicalRecord medicalRecord = new MedicalRecord("Sayd","Julien","14/04/1991",medications,allergies);
    private final MedicalRecord medicalRecordModified = new MedicalRecord("Saydou","Julien","14/04/1991",medications,allergies);
    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addAMedicalRecord() throws Exception {

        when(medicalRecordRepository.addNewMedicalRecord(medicalRecord)).thenReturn(medicalRecord);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/medicalRecord")
                        .content(asJsonString(medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("14/04/1991"));
    }
    @Test
    void deleteAMedicalRecord() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                    .delete("/medicalRecord")
                        .content(asJsonString(medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void udaptMedicalRecord() throws Exception {
        when(medicalRecordRepository.udapteMedicalRecord(medicalRecord)).thenReturn(medicalRecordModified);

        mockMvc.perform( MockMvcRequestBuilders
                .put("/medicalRecord")
                        .content(asJsonString(medicalRecord))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
        .andExpect(MockMvcResultMatchers.jsonPath(".firstName").value("Saydou"));

    }
}