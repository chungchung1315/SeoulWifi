package com.marceldev.seoulpublicwifi.Models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Bookmark {
    private int id;
    private String bookmarkName;
    private String wifiName;
    private String wifiManageNumber;
    private String registerDate;

    @Override
    public String toString() {
        return String.format("id: %d, bookmarkName: %s, wifiName: %s, registerDate: %s", id, bookmarkName, wifiName, registerDate);
    }
}
