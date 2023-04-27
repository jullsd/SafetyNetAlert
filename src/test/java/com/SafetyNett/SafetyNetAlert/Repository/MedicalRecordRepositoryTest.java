package com.SafetyNett.SafetyNetAlert.Repository;


import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import com.SafetyNett.SafetyNetAlert.repository.MedicalRecordRepositoryDataMemory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@ExtendWith( MockitoExtension.class)
public class MedicalRecordRepositoryTest {

    @Mock
    private static DataReaderFromAJson jsonReaderRepository;

    private List<MedicalRecord> medicalRecordsInData = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();
    private List<String> medications = new ArrayList<>();

    private  MedicalRecord medicalRecord = new MedicalRecord("Sayd","Julien","14/04/1991",medications,allergies);

    @Test
    public void findAMedicalRecordByLastNAmeAndFirstNametest() {

        MedicalRecordRepositoryDataMemory medicalRecordRepository = new MedicalRecordRepositoryDataMemory(jsonReaderRepository);

       medicalRecordRepository.addNewMedicalRecord(medicalRecord);



        MedicalRecord medicalRecord = medicalRecordRepository.findByLastNameAndFirstName("Julien","Sayd");

        assertThat(medicalRecord).isEqualTo(this.medicalRecord);

    }

    @Test
    public void AddANewMedicalRecord() {

        MedicalRecordRepositoryDataMemory medicalRecordRepository = new MedicalRecordRepositoryDataMemory(jsonReaderRepository);

        medicalRecordRepository.addNewMedicalRecord(medicalRecord);

        List  medicalRecords = medicalRecordRepository.findAll();

        assertThat(medicalRecords).contains(medicalRecord);
    }

    @Test
    public void DontFindAMedicalRecordByFirstName() {

        MedicalRecordRepositoryDataMemory medicalRecordRepository = new MedicalRecordRepositoryDataMemory(jsonReaderRepository);

        medicalRecordRepository.addNewMedicalRecord(medicalRecord);



        assertThrows(IllegalArgumentException.class, () ->medicalRecordRepository.findByLastNameAndFirstName("Jul","Sayd"));

    }
    @Test
    public void DontfindAMedicalRecordByLastNAme() {

        MedicalRecordRepositoryDataMemory medicalRecordRepository = new MedicalRecordRepositoryDataMemory(jsonReaderRepository);

        medicalRecordRepository.addNewMedicalRecord(medicalRecord);




        assertThrows(IllegalArgumentException.class, () ->medicalRecordRepository.findByLastNameAndFirstName("Julien","Sayde"));
    }

    @Test
    public void DeleteAMedicalRecord() {

        MedicalRecordRepositoryDataMemory medicalRecordRepository = new MedicalRecordRepositoryDataMemory(jsonReaderRepository);
        medicalRecordRepository.addNewMedicalRecord(medicalRecord);

       medicalRecordRepository.deleteAMedicalRecord(medicalRecord);

        List medicalRecordsinData = medicalRecordRepository.findAll();

        assertThat(medicalRecordsinData).doesNotContain(medicalRecord);
    }


    @Test
    public void udapteInformationOfAMedicalRecordTest() {

        MedicalRecordRepositoryDataMemory medicalRecordRepository = new MedicalRecordRepositoryDataMemory(jsonReaderRepository);
        medicalRecordRepository.addNewMedicalRecord(medicalRecord);

        allergies.add("cacahuete");

        medicalRecord.setAllergies(allergies);
        medicalRecord.setLastName("michel");

        medicalRecordRepository.udapteMedicalRecord(medicalRecord);

        assertThat(medicalRecord.getAllergies()).contains("cacahuete");
        assertThat(medicalRecord.getLastName()).isEqualTo("michel");

    }






}
