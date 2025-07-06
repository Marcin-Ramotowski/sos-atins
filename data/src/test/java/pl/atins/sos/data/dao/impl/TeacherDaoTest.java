package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.TeacherDao;
import pl.atins.sos.model.EmploymentType;
import pl.atins.sos.model.Teacher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class TeacherDaoTest extends BaseIntegrationTest {

    @Autowired
    private TeacherDao teacherDao;

    @Test
    @Commit
    void teacherIsCreatedAndRead() {
        Teacher expected = constructAndPersistNewTeacher();
        flushContext();

        Optional<Teacher> teacherOptional = teacherDao.findById(expected.getId());
        assertTrue(teacherOptional.isPresent());
        Teacher actual = teacherOptional.get();

        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getBirthDate(), actual.getBirthDate());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.isAdmin(), actual.isAdmin());
        assertEquals(expected.getLogin(), actual.getLogin());
        assertArrayEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.isMfaEnabled(), actual.isMfaEnabled());
        assertEquals(expected.isActive(), actual.isActive());
        assertEquals(expected.getDegree(), actual.getDegree());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getEmploymentType(), actual.getEmploymentType());
        assertEquals(expected.getOfficeNumber(), actual.getOfficeNumber());
        assertEquals(expected.getHireDate(), actual.getHireDate());

        assertEquals(expected.getDepartment().getId(), actual.getDepartment().getId());
    }

    @Test
    @Commit
    void teacherIsCreatedAndDeleted() {
        Teacher expected = constructAndPersistNewTeacher();
        flushContext();

        teacherDao.deleteById(expected.getId());
        flushContext();

        Optional<Teacher> teacherOptional = teacherDao.findById(expected.getId());
        assertFalse(teacherOptional.isPresent());
    }

    @Test
    @Commit
    void teacherIsCreatedAndUpdated() {
        Teacher expected = constructAndPersistNewTeacher();
        flushContext();

        teacherDao.updateById(expected.getId(), teacher -> teacher.setEmploymentType(EmploymentType.PART_TIME));
        flushContext();

        Optional<Teacher> teacherOptional = teacherDao.findById(expected.getId());
        assertTrue(teacherOptional.isPresent());

        Teacher actual = teacherOptional.get();
        assertEquals(EmploymentType.PART_TIME, actual.getEmploymentType());
    }
}
