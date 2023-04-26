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
        log.debug("Call getBirthdayByLastNameAndFirstName with {}&{}",lastName,firstName);
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        for(MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getLastName().equals(lastName) && (medicalRecord.getFirstName().equals(firstName))) {
                log.debug("Respone getBirthdayByLastNameAndFirstName  with {}&{} :{} ",lastName,firstName,medicalRecord.getBirthdate());
                return medicalRecord.getBirthdate();
            }
            log.debug("Failed to getBirthdayByLastNameAndFirstName  with {}&{}",lastName,firstName);
        }

        return null;


    }

    public List<String> getAllergiesByLastNameAndFirstName(String lastName, String firstName) {
        log.debug("Call getAllergiesByLastNameAndFirstName with {}&{}",lastName,firstName);
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        for(MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getLastName().equals(lastName) && (medicalRecord.getFirstName().equals(firstName))) {
                log.debug("Respone getAllergiesByLastNameAndFirstName with {}&{} :{} ",lastName,firstName,medicalRecord.getAllergies());
                return medicalRecord.getAllergies();
            }
            log.debug("Failed to getAllergiesByLastNameAndFirstName with {}&{}",lastName,firstName);
        }
        return null;

    }

    public List<String> getMedicationsByLastNameAndFirstName(String lastName, String firstName) {
        log.debug("Call getMedicationsByLastNameAndFirstName with {}&{}",lastName,firstName);
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        for(MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getLastName().equals(lastName) && (medicalRecord.getFirstName().equals(firstName))) {
                log.debug("Respone getMedicationsByLastNameAndFirstName with {}&{} :{} ",lastName,firstName,medicalRecord.getMedications());
                return medicalRecord.getMedications();
            }
            log.debug("Failed to getMedicationsByLastNameAndFirstName with {}&{}",lastName,firstName);
        }
        return null;

    }


}
