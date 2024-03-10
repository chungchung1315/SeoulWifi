package com.marceldev.seoulpublicwifi.Services;

import com.marceldev.seoulpublicwifi.Constants;
import com.marceldev.seoulpublicwifi.Models.BookmarkGroup;
import com.marceldev.seoulpublicwifi.Models.BookmarkGroupModify;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupService extends BaseService {

    public int add(String name, int priority) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;

        int affectedRow = 0;

        try {
            conn = DriverManager.getConnection(url);
            String sql = "insert into BOOKMARK_GROUP (name, priority, registerdate)\n" +
                    "values (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, priority);
            stmt.setString(3, localDateTimeNow());
            affectedRow = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(null, stmt, conn);
        }

        return affectedRow;
    }

    public List<BookmarkGroup> list() {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);

            String sql = "select *\n" +
                    "from BOOKMARK_GROUP\n" +
                    "order by PRIORITY asc\n";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            List<BookmarkGroup> groups = new ArrayList<>();

            while (rs.next()) {
                BookmarkGroup group = new BookmarkGroup();
                group.setId(rs.getInt("BOOKMARK_GROUP_ID"));
                group.setBookmarkName(rs.getString("NAME"));
                group.setPriority(rs.getInt("PRIORITY"));
                group.setRegisterDate(rs.getString("REGISTERDATE"));
                group.setModifyDate(rs.getString("MODIFYDATE"));

                groups.add(group);
            }

            return groups;

        } catch (SQLException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }
        return null;
    }

    public BookmarkGroup getBookmarkGroup(int id) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);

            String sql = "select *\n" +
                    "from BOOKMARK_GROUP\n" +
                    "where BOOKMARK_GROUP_ID = ?\n";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                BookmarkGroup group = new BookmarkGroup();
                group.setId(rs.getInt("BOOKMARK_GROUP_ID"));
                group.setBookmarkName(rs.getString("NAME"));
                group.setPriority(rs.getInt("PRIORITY"));
                group.setRegisterDate(rs.getString("REGISTERDATE"));
                group.setModifyDate(rs.getString("MODIFYDATE"));

                return group;
            }

            return null;

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
            String bookmarkGroupDeleteSql = "delete \n" +
                    "from BOOKMARK_GROUP\n" +
                    "where BOOKMARK_GROUP_ID = ?\n";
            stmt = conn.prepareStatement(bookmarkGroupDeleteSql);
            stmt.setInt(1, id);
            affectedRows = stmt.executeUpdate();

            // 포함된 Bookmark 삭제
            String bookmarkDeleteSql = "delete\n" +
                    "from BOOKMARK \n" +
                    "where BOOKMARK_GROUP_ID = ?";
            stmt = conn.prepareStatement(bookmarkDeleteSql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedRows;
    }

    public int edit(int id, BookmarkGroupModify bookmarkGroupModify) {
        loadDBDriver();
        String url = Constants.DB_URL;

        Connection conn = null;
        PreparedStatement stmt = null;
        int affectedRow = 0;

        try {
            conn = DriverManager.getConnection(url);
            String sql = "update BOOKMARK_GROUP\n" +
                    "set NAME = ?\n" +
                    "\t, PRIORITY = ?\n" +
                    "\t, MODIFYDATE = ?\n" +
                    "where BOOKMARK_GROUP_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookmarkGroupModify.getBookmarkName());
            stmt.setInt(2, bookmarkGroupModify.getPriority());
            stmt.setString(3, localDateTimeNow());
            stmt.setInt(4, id);
            affectedRow = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedRow;
    }
}
