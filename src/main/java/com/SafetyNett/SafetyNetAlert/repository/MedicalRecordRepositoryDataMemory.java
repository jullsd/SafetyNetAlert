package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepositoryDataMemory implements MedicalRecordRepository {

    @Autowired
    DataReaderFromAJson dataReaderFromAJson;

    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @Autowired
    public MedicalRecordRepositoryDataMemory(DataReaderFromAJson dataReaderFromAJson) {
        this.dataReaderFromAJson = dataReaderFromAJson;
        medicalRecords.addAll(dataReaderFromAJson.medicalRecords());
    }

    @Override
    public List<MedicalRecord> findAll() {

        List<MedicalRecord> medicalRecordsClone = new ArrayList<>(medicalRecords);
        return medicalRecordsClone;
    }

    @Override
    public MedicalRecord addNewMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        return null;
    }

    @Override
    public MedicalRecord findByLastNameAndFirstName(String lastName, String firstName) {
        for(MedicalRecord medicalRecord : medicalRecords) {
            if ((medicalRecord.getFirstName().equals(firstName)) && (medicalRecord.getLastName().equals(lastName))) {
                return medicalRecord;
            }
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
