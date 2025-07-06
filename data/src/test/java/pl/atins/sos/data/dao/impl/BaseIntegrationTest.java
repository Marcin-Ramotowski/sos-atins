package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pl.atins.sos.data.dao.DepartmentDao;
import pl.atins.sos.data.dao.StudentDao;
import pl.atins.sos.data.dao.SubjectDao;
import pl.atins.sos.data.dao.TeacherDao;
import pl.atins.sos.model.Department;
import pl.atins.sos.model.EmploymentType;
import pl.atins.sos.model.Enrollment;
import pl.atins.sos.model.ModeOfStudy;
import pl.atins.sos.model.Specialization;
import pl.atins.sos.model.Student;
import pl.atins.sos.model.Subject;
import pl.atins.sos.model.Teacher;
import pl.atins.sos.model.TitleOfGrade;
import pl.atins.sos.model.UniversityClass;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public abstract class BaseIntegrationTest {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private TeacherDao teacherDao;

    @PersistenceContext
    private EntityManager em;

    protected void flushContext() {
        em.flush();
        em.clear();
    }

    protected Student constructAndPersistNewStudent() {
        Random random = new Random();
        Student student = new Student();
        student.setFirstName("FirstName");
        student.setLastName("LastName");
        student.setBirthDate(LocalDate.now());
        student.setEmail("em@i.l");
        student.setAdmin(false);
        student.setLogin("login" + random.nextInt());
        student.setPassword(Arrays.copyOf("Password".getBytes(StandardCharsets.UTF_8), 64));
        student.setAvgScore(5.0);
        student.setAgreementNum(123);
        student.setStudentNumber(random.nextInt());
        student.setCurrentSemester(1);
        student.setEnrollSemester(1);
        student.setGraduationDate(LocalDate.now());
        student.setEnrollmentYear(LocalDate.now());
        student.setModeOfStudy(ModeOfStudy.FULL_TIME);
        student.setTitleOfGrade(TitleOfGrade.MASTER);
        student.setSpecialization(Specialization.PROGRAMMING);
        student.setMfaEnabled(false);
        student.setActive(true);
        student.setScholarshipHolder(false);

        Optional<Department> department = departmentDao.findById(0L);
        assertTrue(department.isPresent());
        student.setDepartment(department.get());
        studentDao.create(student);
        return student;
    }

    protected Teacher constructAndPersistNewTeacher() {
        Teacher teacher = constructRandomTeacher();
        teacherDao.create(teacher);
        return teacher;
    }

    protected Subject constructAndPersistNewSubject() {
        Subject subject = constructRandomSubject();
        subjectDao.create(subject);
        return subject;
    }

    protected Teacher constructRandomTeacher() {
        Random random = new Random();

        Teacher teacher = new Teacher();

        teacher.setFirstName("FirstName");
        teacher.setLastName("LastName");
        teacher.setBirthDate(LocalDate.now());
        teacher.setEmail("em@i.l");
        teacher.setAdmin(false);
        teacher.setLogin("login" + random.nextInt());
        teacher.setPassword(Arrays.copyOf("Password".getBytes(StandardCharsets.UTF_8), 64));
        teacher.setMfaEnabled(false);
        teacher.setActive(true);
        teacher.setDegree("PhD");
        teacher.setTitle("Dr.");
        teacher.setEmploymentType(EmploymentType.FULL_TIME);
        teacher.setOfficeNumber("101B");
        teacher.setHireDate(LocalDate.now());

        Optional<Department> department = departmentDao.findById(0L);
        assertTrue(department.isPresent());
        teacher.setDepartment(department.get());

        return teacher;
    }

    protected Subject constructRandomSubject() {
        Subject subject = new Subject();

        subject.setType("Subject type");
        subject.setName("Projektowanie i Programowanie Aplikacji Biznesowych");
        subject.setDescription("Subject description");

        return subject;
    }

    protected UniversityClass constructRandomClassForSubjectAndTeacher(Subject subject, Teacher teacher) {
        UniversityClass uniClass = new UniversityClass();

        uniClass.setRoom("1.02");
        uniClass.setWeekDay("Monday");
        uniClass.setType("Lecture");
        uniClass.setStartTime(OffsetTime.now());
        uniClass.setEndTime(OffsetTime.now());
        uniClass.setSubject(subject);
        uniClass.setTeacher(teacher);

        return uniClass;
    }

    protected Enrollment constructEnrollmentForStudentAndSubject(Student student, Subject subject) {
        Enrollment enrollment = new Enrollment();

        enrollment.setSubject(subject);
        enrollment.setStudent(student);
        enrollment.setStatus("Enrolled");
        enrollment.setEnrollmentDate(LocalDate.now());

        return enrollment;
    }
}
