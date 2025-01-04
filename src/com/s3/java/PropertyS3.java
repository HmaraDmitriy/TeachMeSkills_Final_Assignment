package com.s3.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyS3 {
    public static void main(String[] args) {

        String propertiesFilePath = "config.properties";
        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream(propertiesFilePath)) {
            props.load(input);
            String accessKey = props.getProperty("accessKey");
            String secretKey = props.getProperty("secretKey");

            if (accessKey == null || secretKey == null) {
                System.err.println("Error: AccessKey or SecretKey is missing in config.properties");
                return;
            }

            System.out.println("Properties updated successfully!");

        } catch (IOException e) {
            System.err.println("Error config.properties: " + e.getMessage());
        }
    }
}
