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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class EnrollmentDaoTest extends BaseIntegrationTest {

    @Autowired
    private EnrollmentDao enrollmentDao;

    @Test
    @Commit
    void findByStudentId_returnsEnrollmentAfterEnrollment() {
        // Arrange
        Student student = constructAndPersistNewStudent();
        Subject subject = constructAndPersistNewSubject();
        flushContext();

        Enrollment enrollment = constructEnrollmentForStudentAndSubject(student, subject);

        // Act
        enrollmentDao.create(enrollment);
        flushContext();

        List<Enrollment> enrollmentsFromFind = enrollmentDao.findByStudentId(student.getId());

        // Assert
        assertEquals(1, enrollmentsFromFind.size());
        assertTrue(containsEnrollmentWithStudentAndSubject(enrollmentsFromFind, student.getId(), subject.getId()));
    }

    @Test
    @Commit
    void getEnrollmentsForStudent_returnsNoEnrollmentsForNewStudent() {
        // Arrange
        Student student = constructAndPersistNewStudent();
        flushContext();

        // Act
        List<Enrollment> enrollments = enrollmentDao.findByStudentId(student.getId());

        // Assert
        assertEquals(0, enrollments.size());
    }

    @Test
    @Commit
    void getEnrollmentsForStudent_returnsNoEnrollmentsAfterUnregistration() {
        // Arrange
        Student student = constructAndPersistNewStudent();
        Subject subject = constructAndPersistNewSubject();
        flushContext();

        Enrollment enrollment = constructEnrollmentForStudentAndSubject(student, subject);

        // Act
        enrollmentDao.create(enrollment);
        flushContext();

        enrollmentDao.unregisterStudentFromSubject(student.getId(), subject.getId());
        flushContext();

        List<Enrollment> enrollments = enrollmentDao.findByStudentId(student.getId());

        // Assert
        assertEquals(0, enrollments.size());
    }

    private static boolean containsEnrollmentWithStudentAndSubject(List<Enrollment> list, long studentId, long subjectId) {
        return list.stream().anyMatch(e -> {
            Student student = e.getStudent();
            Subject subject = e.getSubject();
            return student != null
                    && student.getId() == studentId
                    && subject != null
                    && subject.getId() == subjectId;
        });
    }
}
