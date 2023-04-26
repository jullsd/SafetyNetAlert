package com.SafetyNett.SafetyNetAlert.service;

import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }


    public String getBirthdayByLastNameAndFirstName(String lastName, String firstName) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        for(MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getLastName().equals(lastName) && (medicalRecord.getFirstName().equals(firstName))) {
                return medicalRecord.getBirthdate();
            }

        }

        return null;


    }

    public List<String> getAllergiesByLastNameAndFirstName(String lastName, String firstName) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        for(MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getLastName().equals(lastName) && (medicalRecord.getFirstName().equals(firstName))) {
                return medicalRecord.getAllergies();
            }

        }
        return null;

    }

    public List<String> getMedicationsByLastNameAndFirstName(String lastName, String firstName) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        for(MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getLastName().equals(lastName) && (medicalRecord.getFirstName().equals(firstName))) {
                return medicalRecord.getMedications();
            }

        }
        return null;

    }


}
