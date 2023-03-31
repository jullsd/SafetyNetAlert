package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepositoryDataMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
public class MedicalRecordsController {

    @Autowired
    MedicalRecordRepositoryDataMemory medicalRecordRepository;

    @GetMapping("/MedicalRecord")
    public List<MedicalRecord> listmedicalRecord() {
        return medicalRecordRepository.findAll();
    }

    @PostMapping("/MedicalRecord")
    public void addAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        medicalRecordRepository.addNewMedicalRecord(medicalRecord);
    }

    @DeleteMapping("/MedicalRecord")
    public void deleteAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordRepository.deleteAMedicalRecord(medicalRecord);

    }

    @PutMapping("/MedicalRecord")
    public MedicalRecord udapteApersonne(@RequestBody MedicalRecord medicalRecord) {

        medicalRecordRepository.udapteMedicalRecord(medicalRecord);

        return medicalRecord;

    }

}
