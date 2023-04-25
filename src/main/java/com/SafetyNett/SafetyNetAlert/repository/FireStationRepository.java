package com.SafetyNett.SafetyNetAlert.repository;

import com.SafetyNett.SafetyNetAlert.model.FireStation;
import com.SafetyNett.SafetyNetAlert.model.Personne;

import java.util.List;

public interface FireStationRepository {


    List<FireStation> findAll();
    FireStation addAFireStation(FireStation fireStation);

    FireStation findByAdresse(String adress);

   void deleteAFireStation(FireStation fireStation);

    FireStation udapteAFireStaion(FireStation fireStation);


}
