# TeachMeSkills_C32_Final_Assignment

Project Description

This project is a Financial Document Processing and Financial Reporting Application.
It provides functionality for processing financial documents, such as invoices, orders, and checks,
which can only be in .txt format. The program calculates key statistics from these documents and outputs
the results both locally and to the AWS S3 cloud storage. In addition to financial document processing,
the program includes secure access with login/password authentication and a two-factor authentication (TFA)
mechanism using OTP codes and QR codes, compatible with applications like Google Authenticator.

The program ensures thorough validation of input data, logging of both general information and errors, a
nd clear separation of logs into separate files. It also performs cleanup by moving invalid files into a
designated folder and securely stores keys and configuration in a properties file.

----------------------------------------Solution-diagram----------------------------------------------------------------

Solution Diagram for Payment Document Processing Program

### 1. Overview

This solution diagram outlines the architecture and flow of a program designed to process
payment documents, generate financial reports, and provide secure access through two-factor authentication.

### 2. Key Components

                                   Authentication Module
* Login and Password Validation: Validates user credentials against a secure database.
* Two-Factor Authentication: Implements OTP generation and QR code scanning using Authenticator apps.

                                   Document Processing Module
* Document Reading: Reads financial documents from a specified folder.
* Validation: Filters files based on naming conventions, structure, and year.
* Classification: Categorizes documents into invoices, orders, and receipts based on their structure.

                                   Statistics Generation Module
* Data Aggregation: Extracts and processes relevant financial data from documents.
* Report Generation: Compiles statistics into a structured report file.

                                   Error Handling and Logging Module
* General Logs: Stores operational logs.
* Error Logs: Captures and records error details.
* File Segregation: Moves invalid files to a separate folder.

                                   Cloud Integration Module
* S3 File Upload: Uploads the final statistics file to Amazon S3.
* Configuration Management: Reads S3 keys and other settings from a properties file.

### 3. Architecture

                                   User Interaction Layer
* User Login Interface: For entering login credentials.
* Two-Factor Verification: Prompts for OTP via Authenticator app.

                                    Core Processing Layer
1) Input:
	- Reads the folder path specified by the user.
	- Fetches configuration settings from the properties file.

2) Document Handling:
	- Validates and categorizes financial documents.
	- Processes only files from the current year.

3) Error Management:
	- Identifies invalid files and moves them to a designated folder.

4) Statistics Compilation:
	- Aggregates and calculates financial data.

5) Report Export:
	- Generates the final report and uploads it to Amazon S3.

	                                  Infrastructure Layer
	- Local Storage: For storing logs and segregated files.
	- Cloud Storage (Amazon S3): For uploading the final statistics report.

### 4. Flow Diagram

Step 1: Authentication
1. User enters login credentials.
2. System validates credentials.
3. User scans QR code and enters OTP.
4. Access is granted upon successful verification.

Step 2: Document Processing
1. User provides the path to the document folder.
2. System scans and identifies files based on:
   --- Naming conventions.
   --- File structure.
   --- File creation/modification date (current year).
3. Valid documents are processed; invalid ones are moved to a separate folder.

Step 3: Data Aggregation
1. Financial data is extracted from valid documents.
2. Data is categorized by document type (invoice, order, check).
3. Summary statistics are generated.

Step 4: Reporting and Storage
1. Final statistics are compiled into a report file.
2. Report is uploaded to Amazon S3.
3. Logs are saved locally (general and error logs).


-----------------------------------------------Sequence Diagram-----------------------------------------------------------

![Sequence Diagram.png](../TeachMeSkills_Final_Assignment-main-2/diagrams/Sequence%20Diagram.png)

