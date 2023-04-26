package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.MedicalRecord;
import com.SafetyNett.SafetyNetAlert.model.Personne;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class DataReaderFromAJson {


    public List<Personne> personne() {

        List<Personne> personnes;

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(Paths.get("src/main/resources/Data.json").toFile());
            ObjectReader reader = mapper.readerFor(new TypeReference<List<Personne>>() {
            });
            personnes = reader.readValue(node.get("persons"));

        } catch (IOException ex) {
            log.debug("Failed to Read json", ex);
            throw new RuntimeException(ex);

        }

        return personnes;
    }


    public List<MedicalRecord> medicalRecords() {

        List<MedicalRecord> medicalRecords;

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(Paths.get("src/main/resources/Data.json").toFile());
            ObjectReader reader = mapper.readerFor(new TypeReference<List<MedicalRecord>>() {
            });
            medicalRecords = reader.readValue(node.get("medicalrecords"));

        } catch (IOException ex) {
            log.debug("Failed to Read json", ex);
            throw new RuntimeException(ex);
        }

        return medicalRecords;
    }

    public List<FireStation> fireStations() {

        List<FireStation> fireStations;

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(Paths.get("src/main/resources/Data.json").toFile());
            ObjectReader reader = mapper.readerFor(new TypeReference<List<FireStation>>() {
            });
            fireStations = reader.readValue(node.get("firestations"));

        } catch (IOException ex) {
            log.debug("Failed to Read json", ex);
            throw new RuntimeException(ex);
        }

        return fireStations;
    }


}



