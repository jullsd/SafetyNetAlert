package com.SafetyNett.SafetyNetAlert.controller;

import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class
MedicalRecordsController {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;


    @PostMapping( "/medicalRecord" )
    public ResponseEntity<MedicalRecord> addAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("Call of addAMedicalRecord with  medicalRecord : {}", medicalRecord);
        try {
            log.info("Add  medicalRecord : {}", medicalRecord);
            medicalRecord = medicalRecordRepository.addNewMedicalRecord(medicalRecord);
            return new ResponseEntity<>(medicalRecord, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Failed to Add medicalRecord  with {}", medicalRecord);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping( "/medicalRecord" )
    public ResponseEntity<HttpStatus> deleteAMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("Call of deleteAMedicalRecord with  medicalRecord : {}", medicalRecord);
        try {
            log.info("Delete firestation : {} ", medicalRecord);
            medicalRecordRepository.deleteAMedicalRecord(medicalRecord);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error("Failed to delete medicalRecord  with {}", medicalRecord);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping( "/medicalRecord" )
    public ResponseEntity<MedicalRecord> udapteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("Call of udapteMedicalRecord with  medicalRecord : {}", medicalRecord);
        try {
            log.info("Udapte medicalRecord : {} ", medicalRecord);
            medicalRecord = medicalRecordRepository.udapteMedicalRecord(medicalRecord);
            return new ResponseEntity<>(medicalRecord, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Failed to udapteMedicalRecord medicalRecord  with {}", medicalRecord);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

}
