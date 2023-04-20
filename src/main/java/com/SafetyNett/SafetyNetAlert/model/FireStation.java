package com.SafetyNett.SafetyNetAlert.model;

import lombok.Data;
import lombok.Generated;
import org.apache.commons.lang3.builder.HashCodeExclude;


@Data
@Generated
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
