package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.DepartmentDao;
import pl.atins.sos.model.Department;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class DepartmentDaoTest extends BaseIntegrationTest {

    public static final String LOCALIZATION = "Localization";
    public static final String HEAD = "Head";
    public static final String NAME = "Name";
    public static final String NEW_NAME = "NewName";
    private static long TEST_ID = 0L;

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    @Order(1)
    @Commit
    void create() {
        Department department = new Department();
        department.setHeadOfDepartment(HEAD);
        department.setLastUpdatedOn(OffsetDateTime.now());
        department.setCreatedOn(OffsetDateTime.now());
        department.setLocalization(LOCALIZATION);
        department.setName(NAME);
        departmentDao.create(department);
        assertNotNull(department.getId());
        TEST_ID = department.getId();
    }

    @Test
    @Order(2)
    void findById() {
        Optional<Department> department = departmentDao.findById(TEST_ID);
        assertNotNull(department);
        assertTrue(department.isPresent());
        assertEquals(HEAD, department.get().getHeadOfDepartment());
        assertTrue(department.get().getCreatedOn().isBefore(OffsetDateTime.now()));
        assertTrue(department.get().getLastUpdatedOn().isBefore(OffsetDateTime.now()));
        assertEquals(LOCALIZATION, department.get().getLocalization());
        assertEquals(NAME, department.get().getName());
    }

    @Test
    @Order(3)
    @Commit
    void update() {
        Optional<Department> department = departmentDao.findById(TEST_ID);
        assertTrue(department.isPresent());
        Department departmentToUpdate = department.get();
        departmentToUpdate.setName(NEW_NAME);
        flushContext();

        Optional<Department> updated = departmentDao.update(departmentToUpdate);
        assertTrue(updated.isPresent());
        assertEquals(departmentToUpdate.getName(), updated.get().getName());
        assertTrue(updated.get().getCreatedOn().isBefore(updated.get().getLastUpdatedOn()));
    }

    @Test
    @Order(4)
    void findAll() {
        assertFalse(departmentDao.findAll().isEmpty());
    }

    @Test
    @Order(5)
    void searchByName() {
        List<Department> departments = departmentDao.searchByName(NEW_NAME);
        assertFalse(departments.isEmpty());
        assertEquals(NEW_NAME, departments.getFirst().getName());
    }

    @Test
    @Order(99)
    @Commit
    void deleteById() {
        departmentDao.deleteById(TEST_ID);
        flushContext();

        assertFalse(departmentDao.findById(TEST_ID).isPresent());
    }
}