package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;

import java.util.List;


public interface MedicalRecordRepository {


    List<MedicalRecord> findAll();

    MedicalRecord addNewMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord findByLastNameAndFirstName(String lastName, String firstName);

    MedicalRecord udapteMedicalRecord(MedicalRecord medicalRecord);

    void deleteAMedicalRecord(MedicalRecord medicalRecord);

}
