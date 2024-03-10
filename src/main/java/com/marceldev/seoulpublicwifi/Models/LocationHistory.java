package com.marceldev.seoulpublicwifi.Models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LocationHistory {
    private int id;
    private double latitude;
    private double longitude;
    private String queryDate;

    @Override
    public String toString() {
        return String.format("id: %d, latitude: %f, longitude: %f, queryDate: %s", id, latitude, longitude, queryDate);
    }
}
