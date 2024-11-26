INSERT INTO students (roll_number, first_name, last_name, email, photograph_path, cgpa, total_credits, graduation_year, domain, specialisation_id, placement_id)
VALUES
('CS101', 'Bhavya', 'Dora', 'bhavya.dora@gmail.com', NULL, 8.75, 150, 2021, NULL, NULL, NULL),
('CS102', 'Suhas', 'Puli', 'suhas.puli@gmail.com', NULL, 7.60, 150, 2020, NULL, NULL, NULL),
('CS103', 'Varun', 'Raj', 'varun.raj@gmail.com', NULL, 9.00, 150, 2022, NULL, NULL, NULL);


INSERT INTO alumni (email, contact_number, student_id)
VALUES
('bhavya.dora@gmail.com', '9876543210', 1),
('suhas.puli@gmail.com', '9876543211', 2),
('varun.raj@gmail.com', '9876543212', 3);

INSERT INTO alumni_education (alumni_id, degree, passing_year, joining_year, college_name, address)
VALUES
(1, 'B.Tech in Computer Science', 2021, 2017, 'IIITB', 'Bangalore'),
(2, 'B.Tech in Computer Science', 2020, 2016, 'IIITB', 'Bangalore'),
(3, 'B.Tech in Computer Science', 2022, 2018, 'IIITB', 'Bangalore');

INSERT INTO organisations (name, address)
VALUES
('Cerebras', 'Bangalore'),
('ADP', 'Hyderabad'),
('Morgan Stanley', 'Bangalore'),
('Qualcomm','Hyderabad');

INSERT INTO alumni_organisation (alumni_id, organisation_id, position, joining_date, leaving_date)
VALUES
(1, 1, 'Software Engineer', '2021-01-15', NULL), 
(2, 2, 'Developer', '2021-06-01', '2023-08-15'), 
(3, 3, 'Developer', '2022-07-01', NULL); 

INSERT INTO alumni_credentials (email, password) VALUES
('bhavya.dora@gmail.com', 'bhavya123'),
('suhas.puli@gmail.com', 'suhas123'),
('varun.raj@gmail.com', 'varun123');
