package com.SafetyNett.SafetyNetAlert.Repository;


import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.repository.DataReaderFromAJson;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith( MockitoExtension.class)
public class MedicalRecordRepositoryTest {

    @Mock
    private static DataReaderFromAJson dataReaderFromAJson;

    private List<MedicalRecord> medicalRecordsInData = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();
    private List<String> medications = new ArrayList<>();








}
