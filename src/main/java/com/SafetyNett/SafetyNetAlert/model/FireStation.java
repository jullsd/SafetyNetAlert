package com.SafetyNett.SafetyNetAlert.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.Objects;


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

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireStation that = ( FireStation ) o;
        return station == that.station && address.equals(that.address);
    }


    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(address, station);
    }

}
