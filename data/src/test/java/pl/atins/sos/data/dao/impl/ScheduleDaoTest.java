package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.CrudDao;
import pl.atins.sos.data.dao.ScheduleDao;
import pl.atins.sos.model.Schedule;
import pl.atins.sos.model.Student;
import pl.atins.sos.model.UniversityClass;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
class ScheduleDaoTest extends BaseIntegrationTest {

    private static long TEST_STUDENT_ID = 1L;
    private static long TEST_CLASS_ID = 0L;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private CrudDao<UniversityClass> classDao;

    @Test
    @Order(1)
    @Commit
    void create() {
        Schedule schedule = new Schedule();
        Student student = constructAndPersistNewStudent();
        assertNotNull(student);
        schedule.setStudent(student);
        Optional<UniversityClass> classUniver = classDao.findById(0L);
        assertTrue(classUniver.isPresent());
        schedule.setUniversityClass(classUniver.get());
        scheduleDao.create(schedule);
        TEST_STUDENT_ID = schedule.getStudent().getId();
        TEST_CLASS_ID = schedule.getUniversityClass().getId();
    }

    @Test
    @Order(2)
    void findById() {
        Optional<Schedule> schedule = scheduleDao.findByStudentId(TEST_STUDENT_ID);
        assertNotNull(schedule);
        assertTrue(schedule.isPresent());
        assertEquals(schedule.get().getStudent().getId(), TEST_STUDENT_ID);
        assertNotNull(schedule.get().getUniversityClass().getId());
    }

    @Test
    @Order(99)
    @Commit
    void deleteById() {
        scheduleDao.deleteById(TEST_STUDENT_ID, TEST_CLASS_ID);
        flushContext();

        assertFalse(scheduleDao.findByStudentId(TEST_STUDENT_ID).isPresent());
    }

}