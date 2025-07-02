package pl.atins.sos.data.dao.impl;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.atins.sos.data.dao.GradeDao;
import pl.atins.sos.data.dao.TeacherDao;
import pl.atins.sos.data.dao.TranscriptDao;
import pl.atins.sos.model.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GradeDaoImplTest extends SpringTest {

    public static final String COMMENT = "Comment";
    public static final double GRADE = 5.0;
    public static final String NEW_COMMENT = "NewComment";
    private static Long TEST_ID = 0L;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TranscriptDao transcriptDao;

    @Test
    @Order(1)
    void create() {
        Grade grade = new Grade();
        grade.setGrade(GRADE);
        grade.setComment(COMMENT);
        Teacher teacher = teacherDao.findById(0L).get();
        grade.setTeacher(teacher);
        Transcript transcript = transcriptDao.findByStudentId(1L).get();
        grade.setTranscript(transcript);
        gradeDao.create(grade);
        assertNotNull(grade.getId());
        TEST_ID = grade.getId();
    }

    @Test
    @Order(2)
    void findById() {
        Optional<Grade> grade = gradeDao.findById(TEST_ID);
        assertTrue(grade.isPresent());
        assertEquals(GRADE, grade.get().getGrade());
        assertTrue(grade.get().getCreatedOn().isBefore(OffsetDateTime.now()));
        assertTrue(grade.get().getLastUpdatedOn().isBefore(OffsetDateTime.now()));
        assertEquals(COMMENT, grade.get().getComment());
        assertNotNull(grade.get().getTeacher());
        assertEquals(0L, grade.get().getTeacher().getId());
//        assertNotNull(grade.get().getTranscript());
//        assertEquals(1L, grade.get().getTranscript().getStudent().getId());
    }

    @Test
    @Order(3)
    void update() {
        Optional<Grade> grade = gradeDao.findById(TEST_ID);
        assertTrue(grade.isPresent());
        Grade gradeToUpdate = grade.get();
        gradeToUpdate.setComment(NEW_COMMENT);
        Optional<Grade> updated = gradeDao.update(gradeToUpdate);
        assertTrue(updated.isPresent());
        assertEquals(gradeToUpdate.getComment(), updated.get().getComment());
        assertTrue(updated.get().getCreatedOn().isBefore(updated.get().getLastUpdatedOn()));
    }

    @Test
    @Order(4)
    void findByStudentId() {
        List<Grade> grades = gradeDao.findByStudentId(1L);
        assertFalse(grades.isEmpty());
    }

    @Test
    @Order(99)
    void deleteById() {
        gradeDao.deleteById(TEST_ID);
        assertFalse(gradeDao.findById(TEST_ID).isPresent());
    }
}