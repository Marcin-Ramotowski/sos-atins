package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pl.atins.sos.data.dao.DepartmentDao;
import pl.atins.sos.data.dao.StudentDao;
import pl.atins.sos.model.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class SpringTest {

    @Autowired
    protected ApplicationContext context;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    @Order(0)
    void contextLoads() {
        assertNotNull(context);
    }

    protected Student getNewStudent() {
        Random random = new Random();
        Student student = new Student();
        student.setFirstName("FirstName");
        student.setLastName("LastName");
        student.setBirthDate(LocalDate.now());
        student.setEmail("em@i.l");
        student.setAdmin(false);
        student.setLogin("login" + random.nextInt());
        student.setPassword("Password".getBytes());
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
}
