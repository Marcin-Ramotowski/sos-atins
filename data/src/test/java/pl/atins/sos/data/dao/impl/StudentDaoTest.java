package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.StudentDao;
import pl.atins.sos.model.Student;
import pl.atins.sos.model.TitleOfGrade;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class StudentDaoTest extends BaseIntegrationTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    @Commit
    void studentIsCreatedAndRead() {
        Student expected = constructAndPersistNewStudent();
        flushContext();

        Optional<Student> studentOptional = studentDao.findById(expected.getId());
        assertTrue(studentOptional.isPresent());
        Student actual = studentOptional.get();

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getBirthDate(), actual.getBirthDate());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.isAdmin(), actual.isAdmin());
        assertEquals(expected.getLogin(), actual.getLogin());
        assertArrayEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getAvgScore(), actual.getAvgScore());
        assertEquals(expected.getAgreementNum(), actual.getAgreementNum());
        assertEquals(expected.getStudentNumber(), actual.getStudentNumber());
        assertEquals(expected.getCurrentSemester(), actual.getCurrentSemester());
        assertEquals(expected.getEnrollSemester(), actual.getEnrollSemester());
        assertEquals(expected.getGraduationDate(), actual.getGraduationDate());
        assertEquals(expected.getEnrollmentYear(), actual.getEnrollmentYear());
        assertEquals(expected.getModeOfStudy(), actual.getModeOfStudy());
        assertEquals(expected.getTitleOfGrade(), actual.getTitleOfGrade());
        assertEquals(expected.getSpecialization(), actual.getSpecialization());
        assertEquals(expected.isMfaEnabled(), actual.isMfaEnabled());
        assertEquals(expected.isActive(), actual.isActive());
        assertEquals(expected.isScholarshipHolder(), actual.isScholarshipHolder());

        assertEquals(expected.getDepartment().getId(), actual.getDepartment().getId());
    }

    @Test
    @Commit
    void studentIsCreatedAndDeleted() {
        Student expected = constructAndPersistNewStudent();
        flushContext();

        studentDao.deleteById(expected.getId());
        flushContext();

        Optional<Student> studentOptional = studentDao.findById(expected.getId());
        assertFalse(studentOptional.isPresent());
    }

    @Test
    @Commit
    void studentIsCreatedAndUpdated() {
        Student expected = constructAndPersistNewStudent();
        flushContext();

        studentDao.updateById(expected.getId(), student -> student.setTitleOfGrade(TitleOfGrade.PHD));
        flushContext();

        Optional<Student> studentOptional = studentDao.findById(expected.getId());
        assertTrue(studentOptional.isPresent());

        Student actual = studentOptional.get();
        assertEquals(TitleOfGrade.PHD, actual.getTitleOfGrade());
    }
}
