package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.EnrollmentDao;
import pl.atins.sos.model.Enrollment;
import pl.atins.sos.model.Student;
import pl.atins.sos.model.Subject;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class EnrollmentDaoTest extends SpringTest {

    @Autowired
    private EnrollmentDao enrollmentDao;

    @Test
    @Commit
    void registersStudentForSubject() {
        // Arrange
        Student student = getNewStudent();
        Subject subject = getNewSubject();

        // Act
        Optional<Enrollment> enrollment = enrollmentDao.registerStudentForSubject(student.getId(), subject.getId());

        // Assert
        assertTrue(enrollment.isPresent());
        assertEquals(student.getId(), enrollment.get().getStudent().getId());
        assertEquals(subject.getId(), enrollment.get().getSubject().getId());
    }

    @Test
    @Commit
    void getEnrollmentsForStudent_returnsNoEnrollmentsForNewStudent() {
        // Arrange
        Student student = getNewStudent();

        // Act
        List<Enrollment> enrollments = enrollmentDao.getEnrollmentsForStudent(student.getId());

        // Assert
        assertEquals(0, enrollments.size());
    }

    @Test
    @Commit
    void getEnrollmentsForStudent_returnsEnrollmentAfterRegistration() {
        // Arrange
        Student student = getNewStudent();
        Subject subject = getNewSubject();

        // Act
        enrollmentDao.registerStudentForSubject(student.getId(), subject.getId());
        List<Enrollment> enrollments = enrollmentDao.getEnrollmentsForStudent(student.getId());

        // Assert
        assertEquals(1, enrollments.size());

        Enrollment enrollment = enrollments.getFirst();
        assertEquals(student.getId(), enrollment.getStudent().getId());
        assertEquals(subject.getId(), enrollment.getSubject().getId());
    }

    @Test
    @Commit
    void getEnrollmentsForStudent_returnsNoEnrollmentsAfterUnregistration() {
        // Arrange
        Student student = getNewStudent();
        Subject subject = getNewSubject();

        // Act
        enrollmentDao.registerStudentForSubject(student.getId(), subject.getId());
        enrollmentDao.unregisterStudentFromSubject(student.getId(), subject.getId());
        List<Enrollment> enrollments = enrollmentDao.getEnrollmentsForStudent(student.getId());

        // Assert
        assertEquals(0, enrollments.size());
    }
}
