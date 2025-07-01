package pl.atins.sos.data.dao.impl;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.atins.sos.data.dao.DepartmentDao;
import pl.atins.sos.model.Department;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentDaoImplTest extends SpringTest {

    public static final String LOCALIZATION = "Localization";
    public static final String HEAD = "Head";
    public static final String NAME = "Name";
    public static final String NEW_NAME = "NewName";
    public long TEST_ID = 1L;
    @Autowired
    DepartmentDao departmentDao;

    @Test
    @Order(1)
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
        assertEquals(NEW_NAME, department.get().getName());
    }

    @Test
    @Order(3)
    void update() {
        Optional<Department> department = departmentDao.findById(TEST_ID);
        assertTrue(department.isPresent());
        Department departmentToUpdate = department.get();
        departmentToUpdate.setName(NEW_NAME);
        Optional<Department> updated = departmentDao.update(departmentToUpdate);
        assertTrue(updated.isPresent());
        assertEquals(departmentToUpdate.getName(), updated.get().getName());
        assertTrue(department.get().getCreatedOn().isBefore(department.get().getLastUpdatedOn()));
    }

    @Test
    @Order(99)
    void deleteById() {
        departmentDao.deleteById(TEST_ID);
        assertFalse(departmentDao.findById(TEST_ID).isPresent());
    }
}