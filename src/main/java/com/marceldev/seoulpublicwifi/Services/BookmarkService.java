package com.marceldev.seoulpublicwifi.Services;

import com.marceldev.seoulpublicwifi.Constants;
import com.marceldev.seoulpublicwifi.Models.Bookmark;
import com.marceldev.seoulpublicwifi.Models.BookmarkGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService extends BaseService {

    public int add(int bookmarkGroupId, String wifiManageNumber) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;

        int affectedRow = 0;

        try {
            conn = DriverManager.getConnection(url);

            String sql = "insert into BOOKMARK (BOOKMARK_GROUP_ID, MANAGE_NUMBER, REGISTERDATE)\n" +
                    "values (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookmarkGroupId);
            stmt.setString(2, wifiManageNumber);
            stmt.setString(3, localDateTimeNow());
            affectedRow = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, stmt, conn);
        }

        return affectedRow;
    }

    public Bookmark detail(int bookmarkId) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            String sql = "select b.BOOKMARK_ID\n" +
                    "\t, b.REGISTERDATE\n" +
                    "\t, bg.BOOKMARK_GROUP_ID\n" +
                    "\t, bg.NAME as BOOKMARK_GROUP_NAME\n" +
                    "\t, w.WIFI_NAME as WIFI_NAME\n" +
                    "\t, w.MANAGE_NUMBER as WIFI_MANAGE_NUMBER\n" +
                    "from BOOKMARK b\n" +
                    "\tjoin BOOKMARK_GROUP bg on b.BOOKMARK_GROUP_ID = bg.BOOKMARK_GROUP_ID \n" +
                    "\tleft join WIFI w on b.MANAGE_NUMBER = w.MANAGE_NUMBER \n" +
                    "where BOOKMARK_ID = ?\n";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookmarkId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(rs.getInt("BOOKMARK_ID"));
                bookmark.setBookmarkName(rs.getString("BOOKMARK_GROUP_NAME"));
                bookmark.setWifiName(rs.getString("WIFI_NAME"));
                bookmark.setWifiManageNumber(rs.getString("WIFI_MANAGE_NUMBER"));
                bookmark.setRegisterDate(rs.getString("REGISTERDATE"));
                return bookmark;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }
        return null;
    }

    public List<Bookmark> list() {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);

            String sql = "select b.BOOKMARK_ID\n" +
                    "\t, b.REGISTERDATE\n" +
                    "\t, bg.BOOKMARK_GROUP_ID\n" +
                    "\t, bg.NAME as BOOKMARK_GROUP_NAME\n" +
                    "\t, w.WIFI_NAME as WIFI_NAME\n" +
                    "\t, w.MANAGE_NUMBER as WIFI_MANAGE_NUMBER\n" +
                    "from BOOKMARK b\n" +
                    "\tjoin BOOKMARK_GROUP bg on b.BOOKMARK_GROUP_ID = bg.BOOKMARK_GROUP_ID \n" +
                    "\tleft join WIFI w on b.MANAGE_NUMBER = w.MANAGE_NUMBER ";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            List<Bookmark> list = new ArrayList<>();

            while (rs.next()) {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(rs.getInt("BOOKMARK_ID"));
                bookmark.setBookmarkName(rs.getString("BOOKMARK_GROUP_NAME"));
                bookmark.setWifiName(rs.getString("WIFI_NAME"));
                bookmark.setWifiManageNumber(rs.getString("WIFI_MANAGE_NUMBER"));
                bookmark.setRegisterDate(rs.getString("REGISTERDATE"));

                list.add(bookmark);
            }

            return list;

        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }
        return null;
    }

    public int delete(int bookmarkId) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;

        int affectedRow = 0;

        try {
            conn = DriverManager.getConnection(url);

            String sql = "delete \n" +
                    "from BOOKMARK\n" +
                    "where BOOKMARK_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookmarkId);
            affectedRow = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, stmt, conn);
        }

        return affectedRow;
    }
}
