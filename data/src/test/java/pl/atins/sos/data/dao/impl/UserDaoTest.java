package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.TeacherDao;
import pl.atins.sos.data.dao.UserDao;
import pl.atins.sos.model.Student;
import pl.atins.sos.model.Teacher;
import pl.atins.sos.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class UserDaoTest extends BaseIntegrationTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TeacherDao teacherDao;

    @Test
    @Commit
    void userIsActivated() {
        Teacher teacher = constructRandomTeacher();
        teacher.setActive(false);
        teacherDao.create(teacher);
        flushContext();

        Optional<Teacher> deactivatedTeacherOptional = teacherDao.findById(teacher.getId());
        assertTrue(deactivatedTeacherOptional.isPresent());

        Teacher deactivatedTeacher = deactivatedTeacherOptional.get();
        assertFalse(deactivatedTeacher.isActive());
        flushContext();

        userDao.activateById(teacher.getId());
        flushContext();

        Optional<Teacher> activatedTeacherOptional = teacherDao.findById(teacher.getId());
        assertTrue(activatedTeacherOptional.isPresent());

        Teacher activatedTeacher = activatedTeacherOptional.get();
        assertTrue(activatedTeacher.isActive());
    }

    @Test
    @Commit
    void findByLoginFindsUser() {
        Student expected = constructAndPersistNewStudent();
        flushContext();

        Optional<User> userOptional = userDao.findByLogin(expected.getLogin());
        assertTrue(userOptional.isPresent());

        User actual = userOptional.get();
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getBirthDate(), actual.getBirthDate());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.isAdmin(), actual.isAdmin());
        assertEquals(expected.getLogin(), actual.getLogin());
        assertArrayEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.isMfaEnabled(), actual.isMfaEnabled());
        assertEquals(expected.isActive(), actual.isActive());
    }

    @Test
    @Commit
    void findByLoginFailsToFindUser() {
        Optional<User> userOptional = userDao.findByLogin("pineapplepizza");
        assertTrue(userOptional.isEmpty());
    }
}
