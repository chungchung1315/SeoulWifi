package com.marceldev.seoulpublicwifi.Services;

import com.google.gson.Gson;
import com.marceldev.seoulpublicwifi.Constants;
import com.marceldev.seoulpublicwifi.Models.WifiInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiService extends BaseService {

    private static final int FETCH_UNIT = Constants.WIFI_API_FETCH_UNIT;

    private int totalCount = 1000;

    public List<WifiInfo> list(Double lat, Double lnt) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            // Haversine Formula. 6371은 Earth의 반지름. 지구를 완전한 구 라고 가정했을 때의 계산
            String sql = "select *\n" +
                    ", (6371 * acos(cos(radians(latitude)) \n" +
                    "   * cos(radians(?)) \n" +
                    "   * cos(radians(?) - radians(LONGITUDE)) \n" +
                    "   + sin(radians(latitude)) \n" +
                    "   * sin(radians(?)))) AS DISTANCE_IN_KM \n" +
                    "from WIFI\n" +
                    "order by DISTANCE_IN_KM asc\n" +
                    "limit 20";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, lat);
            stmt.setDouble(2, lnt);
            stmt.setDouble(3, lat);
            rs = stmt.executeQuery();

            List<WifiInfo> infos = new ArrayList<>();

            while (rs.next()) {
                WifiInfo info = new WifiInfo();
                info.setManageNumber(rs.getString("MANAGE_NUMBER"));
                info.setManageCity(rs.getString("MANAGE_CITY"));
                info.setWifiName(rs.getString("WIFI_NAME"));
                info.setAddressRoad(rs.getString("ADDRESS_ROAD"));
                info.setAddressDetail(rs.getString("ADDRESS_DETAIL"));
                info.setInstallFloor(rs.getString("INSTALL_FLOOR"));
                info.setInstallType(rs.getString("INSTALL_TYPE"));
                info.setInstallInstitute(rs.getString("INSTALL_INSTITUTE"));
                info.setServiceKind(rs.getString("SERVICE_KIND"));
                info.setWifiKind(rs.getString("WIFI_KIND"));
                info.setInstallYear(rs.getString("INSTALL_YEAR"));
                info.setInOutDoor(rs.getString("INOUTDOOR"));
                info.setConnectionEnv(rs.getString("CONNECTION_ENV"));
                info.setLatitude(rs.getString("LATITUDE"));
                info.setLongitude(rs.getString("LONGITUDE"));
                info.setWorkDate(rs.getString("WORKDATE"));

                // 거리르는 별도로 계산해서 나옴
                info.setDistance(rs.getDouble("DISTANCE_IN_KM"));

                infos.add(info);
            }

            return infos;

        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }
        return null;
    }

    public WifiInfo detail(String manageNumber) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            String sql = "select *\n" +
                    "from WIFI\n" +
                    "where MANAGE_NUMBER = ?\n";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, manageNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                WifiInfo info = new WifiInfo();
                info.setManageNumber(rs.getString("MANAGE_NUMBER"));
                info.setManageCity(rs.getString("MANAGE_CITY"));
                info.setWifiName(rs.getString("WIFI_NAME"));
                info.setAddressRoad(rs.getString("ADDRESS_ROAD"));
                info.setAddressDetail(rs.getString("ADDRESS_DETAIL"));
                info.setInstallFloor(rs.getString("INSTALL_FLOOR"));
                info.setInstallType(rs.getString("INSTALL_TYPE"));
                info.setInstallInstitute(rs.getString("INSTALL_INSTITUTE"));
                info.setServiceKind(rs.getString("SERVICE_KIND"));
                info.setWifiKind(rs.getString("WIFI_KIND"));
                info.setInstallYear(rs.getString("INSTALL_YEAR"));
                info.setInOutDoor(rs.getString("INOUTDOOR"));
                info.setConnectionEnv(rs.getString("CONNECTION_ENV"));
                info.setLatitude(rs.getString("LATITUDE"));
                info.setLongitude(rs.getString("LONGITUDE"));
                info.setWorkDate(rs.getString("WORKDATE"));
                return info;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }

        return null;
    }

    public void emptyWifiTable() {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(url);
            String sql = "delete from WIFI";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("WIFI 테이블 비우기 성공");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(null, stmt, conn);
        }
    }

    public int saveToDB(List<WifiInfo> wifis) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;

        int affectedRowSum = 0;

        try {
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);

            String sql = "insert into WIFI (\n" +
                    "\tMANAGE_NUMBER, \n" +
                    "\tMANAGE_CITY, \n" +
                    "\tWIFI_NAME, \n" +
                    "\tADDRESS_ROAD, \n" +
                    "\tADDRESS_DETAIL, \n" +
                    "\tINSTALL_FLOOR, \n" +
                    "\tINSTALL_TYPE, \n" +
                    "\tINSTALL_INSTITUTE, \n" +
                    "\tSERVICE_KIND, \n" +
                    "\tWIFI_KIND, \n" +
                    "\tINSTALL_YEAR,\n" +
                    "\tINOUTDOOR, \n" +
                    "\tCONNECTION_ENV, \n" +
                    "\tLATITUDE, \n" +
                    "\tLONGITUDE, \n" +
                    "\tWORKDATE\n" +
                    ") values (\n" +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ?, " +
                    " ?, ?, ?, ? " +
                    ")";

            stmt = conn.prepareStatement(sql);

            for (WifiInfo info : wifis) {
                stmt.setString(1, info.getManageNumber());
                stmt.setString(2, info.getManageCity());
                stmt.setString(3, info.getWifiName());
                stmt.setString(4, info.getAddressRoad());
                stmt.setString(5, info.getAddressDetail());
                stmt.setString(6, info.getInstallFloor());
                stmt.setString(7, info.getInstallType());
                stmt.setString(8, info.getInstallInstitute());
                stmt.setString(9, info.getServiceKind());
                stmt.setString(10, info.getWifiKind());
                stmt.setString(11, info.getInstallYear());
                stmt.setString(12, info.getInOutDoor());
                stmt.setString(13, info.getConnectionEnv());
                stmt.setString(14, info.getLatitude());
                stmt.setString(15, info.getLongitude());
                stmt.setString(16, info.getWorkDate());
                stmt.addBatch();
            }

            int[] affectedRows = stmt.executeBatch();
            stmt.clearBatch();
            conn.commit();
            conn.setAutoCommit(true);

            for (int affectedRow : affectedRows) {
                affectedRowSum += affectedRow;
            }

            return affectedRowSum;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(null, stmt, conn);
        }
    }

    public List<WifiInfo> fetchWifiAll() {
        List<WifiInfo> list = new ArrayList<>();
        int start = 1;

        while (start <= totalCount) {
            WifiResponseBody res = fetchWifi(start);
            list.addAll(res.getInfo().getRow());
            totalCount = res.getInfo().getTotalCount();

            start += FETCH_UNIT;
        }
        return list;
    }

    public WifiResponseBody fetchWifi(int start) {
        OkHttpClient client = new OkHttpClient();
        String url = makeFetchUrl(start, FETCH_UNIT);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            return gson.fromJson(response.body().string(), WifiResponseBody.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String makeFetchUrl(int start, int offset) {
        String startPage = start + "/";
        String endPage = start + (offset - 1) + "/";
        return Constants.WIFI_URL + Constants.WIFI_API_KEY + Constants.WIFI_API_PATH + startPage + endPage;
    }
}
