package com.cwhite.wedding_photo_box.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

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

    private static final String ZIP_PATH = "home/cwhite56/Pictures/";

    private static final String ZIP_FILE = "/home/cwhite56/Pictures/wedding-photos.zip";

    public static void main(String[]args) throws SQLException, IOException {
        List<String> urlList = createUrlList();
        System.out.println(urlList);
        System.out.println("Found " + urlList.size() + " photos in database.");
        List<String> fileList = downloadFromS3(urlList);
        zipFiles(fileList);
    }

    private static void zipFiles(List<String> fileList) throws IOException {
        FileOutputStream os = new FileOutputStream(ZIP_FILE);
        ZipOutputStream zs = new ZipOutputStream(os);

        byte[] buffer = new byte[1024];

        for (String file : fileList) {
            File fileToZip = new File(ZIP_PATH + file);
            
            FileInputStream is = new FileInputStream(fileToZip);

            zs.putNextEntry(new ZipEntry(fileToZip.getName()));

            int length;

            while((length = is.read(buffer)) > 0) {
                zs.write(buffer, 0, length);
            }
            zs.closeEntry();
        }
    }

    private static List<String> downloadFromS3(List<String> urlList) throws IOException {

        List<String> fileNames = new ArrayList<>();

        S3Client s3Client = S3Client.builder()
        .region(Region.US_EAST_2)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build();

        for (int i = 0; i< urlList.size(); i++) {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(urlList.get(i))
                .build();

            ResponseInputStream<GetObjectResponse> responseInputStream = s3Client.getObject(getObjectRequest);
            GetObjectResponse getObjectResponse = responseInputStream.response();

            String key = getObjectRequest.key();
            try {
                Files.copy(responseInputStream, Paths.get("/home/cwhite56/Pictures/" + key));
            }

            catch (FileAlreadyExistsException e) {
                //e.printStackTrace();
            }

            finally {
                fileNames.add(key);
            }
            
        }
        return fileNames;
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

    