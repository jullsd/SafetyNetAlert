package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.repository.FireStationRepositoryDataMemory;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepositoryDataMemory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith( MockitoExtension.class)
class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepositoryDataMemory medicalRecordRepository;

    private final String LAST_NAME = "Dupont";
    private final String OTHER_LAST_NAME = "Tartopian";

    private final String OTHER_FIRST_NAME = "Tartopian";

    private final String FIRST_NAME = "Michel";
    private final String BIRTHDATE = "04/14/1991";

    @Test
    void getBirthdayByLastNameAndFirstName() {

        MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordRepository);
        List<MedicalRecord> medicalRecords =  new ArrayList<>();
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

       MedicalRecord medicalRecord = new MedicalRecord(FIRST_NAME,LAST_NAME,BIRTHDATE,medications,allergies);
        medicalRecords.add(medicalRecord);

        when(medicalRecordRepository.findAll()).thenReturn(medicalRecords);

        assertThat(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME,FIRST_NAME)).isEqualTo(BIRTHDATE);



    }
    @Test
    void GetBirthdayByLastNameAndFirstNameWithAnOtherLastName() {

        MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordRepository);
        List<MedicalRecord> medicalRecords =  new ArrayList<>();
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(FIRST_NAME,LAST_NAME,BIRTHDATE,medications,allergies);
        medicalRecords.add(medicalRecord);

        when(medicalRecordRepository.findAll()).thenReturn(medicalRecords);

        assertThat(medicalRecordService.getBirthdayByLastNameAndFirstName(OTHER_LAST_NAME,FIRST_NAME)).isNull();



    }

    @Test
    void GetBirthdayByLastNameAndFirstNameWithAnOtherFirstName() {

        MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordRepository);
        List<MedicalRecord> medicalRecords =  new ArrayList<>();
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecord medicalRecord = new MedicalRecord(FIRST_NAME,LAST_NAME,BIRTHDATE,medications,allergies);
        medicalRecords.add(medicalRecord);

        when(medicalRecordRepository.findAll()).thenReturn(medicalRecords);

        assertThat(medicalRecordService.getBirthdayByLastNameAndFirstName(LAST_NAME,OTHER_FIRST_NAME)).isNull();



    }

}