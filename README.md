# ALUMNI PORTAL

## PROBLEM STATEMENT
8.3 Allow alumni to login and update their details such as contact information, educational
qualification and add/update/delete organisations(Drop Down Select) worked/working for

## Features

- **Authentication**: Login functionality for alumni.
- **Contact Information Management**: Update and retrieve alumni contact details.
- **Education Qualification Management**: Update, and retrieve education qualifications for alumni.

## SQL Scripts
- **Creation**:create_academicerp.sql
- **Insertion**:insert_academicerp.sql

## Endpoints

#### Login
- **Endpoint**: `POST /api/v1/alumni/login`
- **Description**: Authenticate alumni using their credentials.
- **Request Body**:
  ```json
  {
    "email": "example@example.com",
    "password": "password123"
  }


#### Get Details
- **Endpoint**: `GET http://localhost:8082/api/v1/alumni/{{id}}`
- **Description**: Get alumni details using AlumniID. 


#### Update Personal Info
- **Endpoint**: `PUT http://localhost:8082/api/v1/alumni/{{id}}`
- **Description**: Update alumni details using AlumniID.
- **Request Params**:newEmail,newContact
  

#### Get Education Info
- **Endpoint**: `GET http://localhost:8082/api/v1/alumni/education/{{alumniId}}`
- **Description**: Get education details using AlumniID. 


#### Update Education Info
- **Endpoint**: `POST http://localhost:8082/api/v1/alumni/education/{{alumniId}}`
- **Description**: Update education details using AlumniID. 
- **Request Body**:
  ```json
  {
  "degree": "",
  "passing_year": 0,
  "joining_year": 0,
  "college_name": "",
  "address": ""
    }


#### Get All Organisations
- **Endpoint**: `GET http://localhost:8082/api/v1/alumni/org`
- **Description**: Get list of organisations. 


#### Get Alumni_Organisation
- **Endpoint**: `GET http://localhost:8082/api/v1/alumni/org/{{alumniId}}`
- **Description**: Get list of organisation details corresponding to given alumniId. 

#### Get Alumni_Organisation
- **Endpoint**: `GET http://localhost:8082/api/v1/alumni/orgbyorg/{{orgId}`
- **Description**: Get organisation details corresponding to given orgId. 

#### Add Alumni Organisation Info
- **Endpoint**: `POST http://localhost:8082/api/v1/alumni/org`
- **Description**: Add Alumni_Organisation details. 
- **Request Body**:
  ```json
  {
  "alumni_id": 0,
  "organisation_id": 0,
  "position": "",
  "joining_date": "",
  "leaving_date": ""
    }


#### Update Alumni Organisation Info
- **Endpoint**: `PUT http://localhost:8082/api/v1/alumni/org/{{id}}`
- **Description**: Update Alumni_Organisation details using AlumniID. 
- **Request Body**:
  ```json
  {
  "alumni_id": 0,
  "organisation_id": 0,
  "position": "",
  "joining_date": "",
  "leaving_date": ""
    }

#### Delete Alumni_Organisation
- **Endpoint**: `DELETE http://localhost:8082/api/v1/alumni/org/{{id}}`
- **Description**: Get list of organisation details corresponding to given alumniId. 

## FrontEnd Pages
- **Login**: for Login
- **DashBoard**: to display personal,education Info
- **UpdateOrgDetails**: to display Organisation Details
- **EditPersonal**: to edit personal info
- **EditEducation**: to edit education qualification
- **EditOrganisation**: to edit organisation details
- **AddOrganisation**: to add organisation