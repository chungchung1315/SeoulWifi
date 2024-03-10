package com.marceldev.seoulpublicwifi;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConfig {
    public static void main(String[] args) {
        deleteDB();
        createDBIfNotPresent();
    }

    public static void createDBIfNotPresent() {
        File file = new File(Constants.DB_FILE);
        if (file.exists()) {
            System.out.println("DB 파일이 존재함");
        } else {
            System.out.println("DB 파일이 존재하지 않음. 새롭게 생성");
            createTableWifi();
            createTableLocationHistory();
            createTableBookmarkGroup();
            createTableBookmark();
        }
    }

    public static void deleteDB() {
        File file = new File(Constants.DB_FILE);
        if (file.delete()) {
            System.out.println("DB 파일 삭제 성공");
        } else {
            System.out.println("DB 파일 삭제 실패");
        }
    }

    public static void createTableLocationHistory() {
        String url = Constants.DB_URL;

        loadDBDriver();

        String sql = "CREATE TABLE `LOCATION_HISTORY` (\n" +
                "\t`LOCATION_HISTORY_ID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, -- ID\n" +
                "\t`LATITUDE`            DOUBLE  NULL,     -- X좌표\n" +
                "\t`LONGITUDE`           DOUBLE  NULL,     -- Y좌표\n" +
                "\t`QUERYDATE`           DATE    NULL      -- 조회일자\n" +
                ")";

        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            conn.close();
            System.out.println("LOCATION_HISTORY 테이블 생성");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTableWifi() {
        String url = Constants.DB_URL;

        loadDBDriver();

        String sql = "CREATE TABLE `WIFI` (\n" +
                "\t`MANAGE_NUMBER`     VARCHAR(20)  NOT NULL PRIMARY KEY, -- 관리번호\n" +
                "\t`MANAGE_CITY`       VARCHAR(20)  NULL,     -- 자치구\n" +
                "\t`WIFI_NAME`         VARCHAR(255) NULL,     -- 와이파이명\n" +
                "\t`ADDRESS_ROAD`      VARCHAR(255) NULL,     -- 도로명주소\n" +
                "\t`ADDRESS_DETAIL`    VARCHAR(255) NULL,     -- 상세주소\n" +
                "\t`INSTALL_FLOOR`     VARCHAR(20)  NULL,     -- 설치위치(층)\n" +
                "\t`INSTALL_TYPE`      VARCHAR(100) NULL,     -- 설치유형\n" +
                "\t`INSTALL_INSTITUTE` VARCHAR(100) NULL,     -- 설치기관\n" +
                "\t`SERVICE_KIND`      VARCHAR(20)  NULL,     -- 서비스구분\n" +
                "\t`WIFI_KIND`         VARCHAR(20)  NULL,     -- 망종류\n" +
                "\t`INSTALL_YEAR`      INTEGER      NULL,     -- 설치년도\n" +
                "\t`INOUTDOOR`         VARCHAR(20)  NULL,     -- 실내외구분\n" +
                "\t`CONNECTION_ENV`    VARCHAR(100) NULL,     -- WIFI접속환경\n" +
                "\t`LATITUDE`          DOUBLE       NULL,     -- X좌표\n" +
                "\t`LONGITUDE`         DOUBLE       NULL,     -- Y좌표\n" +
                "\t`WORKDATE`          VARCHAR(21)  NULL      -- 작업일자\n" +
                ")";

        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            conn.close();
            System.out.println("WIFI 테이블 생성");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTableBookmarkGroup() {
        String url = Constants.DB_URL;

        loadDBDriver();

        String sql = "CREATE TABLE `BOOKMARK_GROUP` (\n" +
                "\t`BOOKMARK_GROUP_ID` INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT, -- ID\n" +
                "\t`NAME`              VARCHAR(100) NULL,     -- 북마크 이름\n" +
                "\t`PRIORITY`          INTEGER      NULL,     -- 순서\n" +
                "\t`REGISTERDATE`      DATE         NULL,     -- 등록일자\n" +
                "\t`MODIFYDATE`        DATE         NULL      -- 수정일자\n" +
                ")";

        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            conn.close();
            System.out.println("BOOKMARK_GROUP 테이블 생성");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTableBookmark() {
        String url = Constants.DB_URL;

        loadDBDriver();

        String sql = "CREATE TABLE `BOOKMARK` (\n" +
                "\t`BOOKMARK_ID`       INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT, -- ID\n" +
                "\t`BOOKMARK_GROUP_ID` INTEGER     NOT NULL, -- ID2\n" +
                "\t`MANAGE_NUMBER`     VARCHAR(20) NULL,     -- 관리번호\n" +
                "\t`REGISTERDATE`      DATE        NULL,      -- 등록일자\t\n" +
                "\tFOREIGN KEY (BOOKMARK_GROUP_ID) REFERENCES BOOKMARK_GROUP(BOOKMARK_GROUP_ID),\n" +
                "\tFOREIGN KEY (MANAGE_NUMBER) REFERENCES WIFI(MANAGE_NUMBER)\n" +
                ")";

        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            conn.close();
            System.out.println("BOOKMARK 테이블 생성");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadDBDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
