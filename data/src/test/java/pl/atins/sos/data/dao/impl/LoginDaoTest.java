package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.LoginDao;
import pl.atins.sos.model.Login;
import pl.atins.sos.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class LoginDaoTest extends BaseIntegrationTest {

    @Autowired
    private LoginDao loginDao;

    @Test
    @Commit
    void loginIsCreatedAndPersisted() {
        Student student = constructAndPersistNewStudent();

        Login expectedLogin = new Login();
        expectedLogin.setUser(student);
        expectedLogin.setSucceeded(true);
        loginDao.create(expectedLogin);

        List<Login> logins = loginDao.findByUserId(student.getId());
        assertEquals(1, logins.size());

        Login actualLogin = logins.getFirst();
        assertEquals(student.getId(), actualLogin.getUser().getId());
        assertTrue(actualLogin.isSucceeded());
    }
}
