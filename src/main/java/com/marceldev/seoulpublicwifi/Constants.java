package com.marceldev.seoulpublicwifi;

public final class Constants {
    private Constants() {}

    public static final String DB_FILE = "test-seoul-wifi-2u28w8.db";
    public static final String DB_URL = "jdbc:sqlite:" + DB_FILE;

    // API - http://openapi.seoul.go.kr:8088/apikey/json/TbPublicWifiInfo/1/10/;
    public static final String WIFI_URL = "http://openapi.seoul.go.kr:8088/";
    public static final String WIFI_API_KEY = "6d4a536158636875313038525459495a";
    public static final String WIFI_API_PATH = "/json/TbPublicWifiInfo/";

    public static final int WIFI_API_FETCH_UNIT = 1000;
}
