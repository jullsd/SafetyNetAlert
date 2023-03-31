package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.model.Personne;

import java.util.List;


public interface MedicalRecordRepository {


    List<MedicalRecord> findAll();
    MedicalRecord addNewMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord findByLastNameAndFirstName(String lastName, String firstName);

    MedicalRecord udapteMedicalRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> deleteAMedicalRecord(MedicalRecord medicalRecord);

}
