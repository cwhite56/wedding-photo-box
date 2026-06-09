package com.cwhite.wedding_photo_box.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class PhotoDownloader {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final String DB_USER = "postgres";

    private static final String DB_PASSWORD = "password";

    private static final String BUCKET_NAME = "amzn-s3-wedding-photo-box-2027";

    public static void main(String[]args) throws SQLException {
        List<String> urlList = createUrlList();
        System.out.println(urlList);
        System.out.println("Found " + urlList.size() + " photos in database.");
        //downloadFromS3(urlList);
    }

    private static void downloadFromS3(List<String> urlList) {
        S3Client s3Client = S3Client.builder()
        .region(Region.US_EAST_2)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build();

        // loop using urlList
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(BUCKET_NAME)
            .key(//file path)
            .build();

        ResponseInputStream<GetObjectResponse> responseStream = s3Client.getObject(getObjectRequest);
        GetObjectResponse getObjectResponse = responseStream.response();


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

    