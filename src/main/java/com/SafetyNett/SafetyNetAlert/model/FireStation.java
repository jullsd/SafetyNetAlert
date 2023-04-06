package com.SafetyNett.SafetyNetAlert.model;

import lombok.Data;
import org.apache.commons.lang3.builder.HashCodeExclude;


@Data
public class FireStation {



    private String address;
    private int station;

    public FireStation() {
    }

    public FireStation(String address, int station) {
        this.address = address;
        this.station = station;
    }
    @Override
    public String toString() {
        return "FireStations{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }

}
