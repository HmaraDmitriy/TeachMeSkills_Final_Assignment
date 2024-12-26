package com.courseproject.twoFA;

import com.courseproject.exception.fileException.WrongLoginException;

import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.Scanner;

public class twoFARunner {

    public static boolean launchTFA() {
        Scanner scanner = new Scanner(System.in);

        String secretKey = twoFAHelpers.generateSecretKey();
        System.out.println("Generated Secret Key: " + secretKey);

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your company name: ");
        String companyName = scanner.nextLine();

        String barCodeUrl = twoFAHelpers.getGoogleAuthenticatorBarCode(secretKey, email, companyName);
        System.out.println("Google Authenticator URL: " + barCodeUrl);

        try {
            String filePath = "QRCode.png";
            twoFAHelpers.createQRCode(barCodeUrl, filePath, 400, 400);
            System.out.println("QR Code generated and saved to " + filePath);
        } catch (WriterException | IOException e) {
            Loggers.errorLogs("Error creating QR code: " + e.getMessage());
            throw new RuntimeException("Error creating QR code", e);
        }

        System.out.print("Please enter the 2FA code: ");
        String inputCode = scanner.nextLine();

        if (inputCode.equals(twoFAHelpers.getTOTPCode(secretKey))) {
            Loggers.actionLogs("Two-factor authentication completed successfully");
            System.out.println("Authentication successful!");
            return true;
        } else {
            WrongLoginException loginFailedException = new WrongLoginException("Error: Invalid authentication code", new Throwable("Invalid code"));
            Loggers.errorLogs(loginFailedException.getMessage());
            System.out.println("Authentication failed. Invalid code.");
            throw loginFailedException;
        }
    }

    public static void main(String[] args) {
        try {
            boolean isAuthenticated = launchTFA();
            if (isAuthenticated) {
                System.out.println("Welcome to the application!");
            }
        } catch (WrongLoginException e) {
            System.err.println("Authentication error: " + e.getMessage());
        }
    }
}
