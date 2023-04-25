package com.SafetyNett.SafetyNetAlert.controller;


import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepository;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepositoryDataMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan
public class
MedicalRecordsController {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;


    @PostMapping("/medicalRecord")
    public ResponseEntity addAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        medicalRecordRepository.addNewMedicalRecord(medicalRecord);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/medicalRecord")
    public ResponseEntity deleteAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordRepository.deleteAMedicalRecord(medicalRecord);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/medicalRecord")
    public MedicalRecord udapteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        medicalRecordRepository.udapteMedicalRecord(medicalRecord);

        return medicalRecord;

    }

}
