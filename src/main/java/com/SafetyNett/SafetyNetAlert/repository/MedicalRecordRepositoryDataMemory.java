package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class MedicalRecordRepositoryDataMemory implements MedicalRecordRepository {

    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @Autowired
    public MedicalRecordRepositoryDataMemory(DataReaderFromAJson dataReaderFromAJson) {
        medicalRecords.addAll(dataReaderFromAJson.medicalRecords());
    }

    @Override
    public List<MedicalRecord> findAll() {

        List<MedicalRecord> medicalRecordsClone = new ArrayList<>(medicalRecords);
        log.debug("Response to findAll(): {}" , medicalRecords);
        return medicalRecordsClone;
    }

    @Override
    public MedicalRecord addNewMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        return null;
    }

    @Override
    public MedicalRecord findByLastNameAndFirstName(String lastName, String firstName) {
        log.debug("Call findByLastNameAndFirstName with {}&{}",lastName,firstName);
        for(MedicalRecord medicalRecord : medicalRecords) {
            if ((medicalRecord.getFirstName().equals(firstName)) && (medicalRecord.getLastName().equals(lastName))) {
                log.debug("Respone findByLastNameAndFirstName with {} {} : {}", lastName,firstName,medicalRecord);
                return medicalRecord;
            }
            log.debug("Failed to findByLastNameAndFirstName with {}&{}",lastName,firstName);
        }
        return null;
    }

    @Override
    public MedicalRecord udapteMedicalRecord(MedicalRecord medicalRecord) {
        int personneIndex = medicalRecords.indexOf(findByLastNameAndFirstName(medicalRecord.getLastName(), medicalRecord.getFirstName()));
        medicalRecords.set(personneIndex, medicalRecord);

        return medicalRecord;
    }

    @Override
    public void deleteAMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecord = findByLastNameAndFirstName(medicalRecord.getLastName(), medicalRecord.getFirstName());
        medicalRecords.remove(medicalRecord);

    }
}
