package com.cwhite.wedding_photo_box.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;



public class PhotoDownloader {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final String DB_USER = "postgres";

    private static String DB_PASSWORD = "password";

    public static void main(String[]args) throws SQLException {
        List<String> urlList = createUrlList();
        System.out.println(urlList);
    }

    private static List<String> createUrlList() throws SQLException {
        List<String> urlList = new ArrayList<>();
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT file_path FROM photos");

        while (rs.next()) {
            urlList.add(rs.getString("file_path"));
        }

        return urlList;
    }
}

    