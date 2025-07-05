package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.TranscriptDao;
import pl.atins.sos.model.Student;
import pl.atins.sos.model.Transcript;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class TranscriptDaoImplTest extends SpringTest {
    private static long TEST_ID = 0L;

    @Autowired
    private TranscriptDao transcriptDao;

    @Test
    @Order(1)
    @Commit
    void create() {
        Student student = getNewStudent();
        assertNotNull(student.getId());
        Transcript transcript = new Transcript();
        transcript.setStudent(student);
        transcriptDao.create(transcript);
        assertNotNull(transcript.getId());
        TEST_ID = transcript.getStudent().getId();
    }

    @Test
    @Order(2)
    void findById() {
        Optional<Transcript> transcript = transcriptDao.findByStudentId(TEST_ID);
        assertNotNull(transcript);
        assertTrue(transcript.isPresent());
        assertEquals(transcript.get().getStudent().getId(), TEST_ID);
    }


    @Test
    @Order(99)
    @Commit
    void deleteById() {
        transcriptDao.deleteById(TEST_ID);
        Optional<Transcript> transcript = transcriptDao.findByStudentId(TEST_ID);
        assertFalse(transcript.isPresent());
    }
}