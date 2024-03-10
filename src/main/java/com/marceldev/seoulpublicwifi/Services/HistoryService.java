package com.marceldev.seoulpublicwifi.Services;

import com.marceldev.seoulpublicwifi.Constants;
import com.marceldev.seoulpublicwifi.Models.LocationHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService extends BaseService {

    public List<LocationHistory> list() {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);

            String sql = "select * \n" +
                    "from LOCATION_HISTORY\n" +
                    "order by QUERYDATE desc;\n";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            List<LocationHistory> histories = new ArrayList<>();

            while (rs.next()) {
                LocationHistory history = new LocationHistory();
                history.setId(rs.getInt("LOCATION_HISTORY_ID"));
                history.setLatitude(rs.getDouble("LATITUDE"));
                history.setLongitude(rs.getDouble("LONGITUDE"));
                history.setQueryDate(rs.getString("QUERYDATE"));

                histories.add(history);
            }

            return histories;

        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }
        return null;
    }

    public int delete(int id) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        int affectedRows = 0;

        try {
            conn = DriverManager.getConnection(url);
            String sql = "delete \n" +
                    "from LOCATION_HISTORY \n" +
                    "where LOCATION_HISTORY_ID = ?\n";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            affectedRows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedRows;
    }

    public int add(Double lat, Double lnt) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;

        int affectedRow = 0;

        try {
            conn = DriverManager.getConnection(url);
            String sql = "insert into LOCATION_HISTORY (LATITUDE, LONGITUDE, QUERYDATE)\n" +
                    "values (?, ?, ?)\n";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, lat);
            stmt.setDouble(2, lnt);
            stmt.setString(3, localDateTimeNow());
            affectedRow = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(null, stmt, conn);
        }

        return affectedRow;
    }
}
