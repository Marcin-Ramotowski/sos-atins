-- Country
INSERT INTO Country (code, name, created_on, last_updated_on) VALUES
('PL', 'Poland', NOW(), NOW()),
('DE', 'Germany', NOW(), NOW());

-- City
INSERT INTO City (country_id, code, name, created_on, last_updated_on) VALUES
(0, 'WAW', 'Warsaw', NOW(), NOW()),
(1, 'BER', 'Berlin', NOW(), NOW());

-- Users & Logins
INSERT INTO User (admin, active, mfa_enabled, birth_date, email, first_name, last_name, login, password, created_on, last_updated_on) VALUES
(TRUE, TRUE, TRUE, '1980-05-01', 'j.kowalski@uni.edu', 'Jan', 'Kowalski', 'jkowalski', 'aabbcc', NOW(), NOW()), -- ID=0 (teacher)
(FALSE, TRUE, FALSE, '2000-10-10', 'a.nowak@student.uni.edu', 'Anna', 'Nowak', 'anowak', 'aabbcc', NOW(), NOW()); -- ID=1 (student)

INSERT INTO Login (user_id, succeeded, created_on, last_updated_on) VALUES
(0, TRUE, NOW(), NOW()),
(0, TRUE, NOW(), NOW()),
(1, FALSE, NOW(), NOW()),
(1, TRUE, NOW(), NOW());

-- Addresses
INSERT INTO Address (user_id, city_id, is_default, flat, house, postal_code, street, created_on, last_updated_on) VALUES
(0, 0, TRUE, '10', '5A', '00-001', 'Main Street', NOW(), NOW()),
(1, 0, TRUE, '12', '6B', '00-002', 'University Ave', NOW(), NOW());

-- Department
INSERT INTO Department (created_on, last_updated_on, head_of_department, localization, name) VALUES
(NOW(), NOW(), 'Dr. Maria Curie', 'Building A', 'Computer Science');

-- Teacher
INSERT INTO Teacher (user_id, department_id, degree, employment_type, hire_date, office_number, title) VALUES
(0, 0, 'PhD', 'FULL_TIME', NOW(), '101A', 'Dr.');

-- Student
INSERT INTO Student (user_id, department_id, agreement_num, avg_score, title_of_grade, current_semester, graduation_date, enrollment_year, enroll_semester, mode_of_study, scholarship_holder, specialization, student_number) VALUES
(1, 0, 123456, 4.5, 'BACHELOR', 4, '2025-06-30', '2021-10-01', 1, 'FULL_TIME', TRUE, 'COMPUTER_GRAPHICS', 2021001);

-- Subject
INSERT INTO Subject (description, name, type, created_on, last_updated_on) VALUES
('Introduction to Algorithms', 'Algorithms', 'Lecture', NOW(), NOW()),
('Database Systems', 'Databases', 'Lecture', NOW(), NOW());

-- TeacherSubject
INSERT INTO TeacherSubject (teacher_id, subject_id) VALUES
(0, 0),
(0, 1);

-- Class
INSERT INTO Class (subject_id, teacher_id, week_day, start_time, end_time, room, type, created_on, last_updated_on) VALUES
(0, 0, 'Monday', '09:00:00', '10:30:00', 'A1', 'Lecture', NOW(), NOW()),
(1, 0, 'Wednesday', '11:00:01', '12:30:00', 'B2', 'Lecture', NOW(), NOW());

-- Enrollment
INSERT INTO Enrollment (subject_id, student_id, enrollment_date, status, created_on, last_updated_on) VALUES
(0, 1, '2024-10-01', 'Enrolled', NOW(), NOW()),
(1, 1, '2024-10-01', 'Enrolled', NOW(), NOW());

-- Schedule
INSERT INTO Schedule (student_id, class_id) VALUES
(1, 0),
(1, 1);

-- Grade
INSERT INTO Grade (teacher_id, created_on, last_updated_on, comment, grade) VALUES
(0, NOW(), NOW(), 'Good progress', 4.0),
(0, NOW(), NOW(), 'Excellent', 5.0);

-- Transcript
INSERT INTO Transcript (student_id) VALUES
(1);
