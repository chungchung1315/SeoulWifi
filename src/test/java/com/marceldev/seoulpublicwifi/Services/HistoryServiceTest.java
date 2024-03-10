package com.marceldev.seoulpublicwifi.Services;

import com.marceldev.seoulpublicwifi.Models.LocationHistory;

import java.util.List;

public class HistoryServiceTest {

    public static void main(String[] args) {
        new HistoryServiceTest().testInsert();
        new HistoryServiceTest().testSelect();
    }

    public void testSelect() {
        HistoryService service = new HistoryService();
        List<LocationHistory> list = service.list();
        for (LocationHistory locationHistory : list) {
            System.out.println(locationHistory);
        }
    }

    public void testInsert() {
        HistoryService service = new HistoryService();
        int affectedRows = service.add(37.123, 127.123);
        if (affectedRows != 1) {
            throw new RuntimeException();
        }
    }
}
