package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.service.MedicalRecordServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordsControllerTest {

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
    @Disabled
    void addAMedicalRecord() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/medicalRecord")
                        .param("medicalRecord",String.valueOf(new MedicalRecord()))
                        .content(asJsonString(new MedicalRecord()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
@Disabled
    @Test
    void deleteAMedicalRecord() throws Exception {
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        mockMvc.perform( MockMvcRequestBuilders
                    .delete("/medicalRecord")
                    .param("medicalRecord",String.valueOf(new MedicalRecord(MedicalRecordServiceTest.FIRST_NAME,MedicalRecordServiceTest.LAST_NAME,
                            MedicalRecordServiceTest.BIRTHDATE,medications,allergies)))
                    .content(asJsonString(new MedicalRecord(MedicalRecordServiceTest.FIRST_NAME,MedicalRecordServiceTest.LAST_NAME,
                            MedicalRecordServiceTest.BIRTHDATE,medications,allergies)))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    void udaptMedicalRecord() {
    }
}