CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(20) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    photograph_path VARCHAR(255),
    cgpa DECIMAL(3,2),
    total_credits INTEGER,
    graduation_year INT,
    domain INT,
    specialisation_id INT,
    placement_id INT
);

CREATE TABLE alumni (
    alumni_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    contact_number VARCHAR(15),
    student_id INT UNIQUE, 
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);


CREATE TABLE alumni_education (
    education_id INT AUTO_INCREMENT PRIMARY KEY,
    alumni_id INT NOT NULL UNIQUE, 
    degree VARCHAR(100),
    passing_year INT,
    joining_year INT,
    college_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    FOREIGN KEY (alumni_id) REFERENCES alumni(alumni_id)
);

CREATE TABLE organisations (
    organisation_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    address TEXT
);

CREATE TABLE alumni_organisation (
    work_id INT AUTO_INCREMENT PRIMARY KEY,
    alumni_id INT NOT NULL, 
    organisation_id INT NOT NULL, 
    position VARCHAR(100),
    joining_date DATE,
    leaving_date DATE,
    FOREIGN KEY (alumni_id) REFERENCES alumni(alumni_id),
    FOREIGN KEY (organisation_id) REFERENCES organisations(organisation_id)
);


CREATE TABLE alumni_credentials (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL, 
    password VARCHAR(20) NOT NULL, 
    FOREIGN KEY (email) REFERENCES alumni(email)
);

