package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.SubjectDao;
import pl.atins.sos.model.Subject;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class SubjectDaoTest extends BaseIntegrationTest {

    @Autowired
    private SubjectDao subjectDao;

    @Test
    @Commit
    void findAll_containsNewSubjectAfterAdd() {
        // Arrange
        Subject addedSubject = constructRandomSubject();

        // Act
        subjectDao.create(addedSubject);
        flushContext();

        List<Subject> subjects = subjectDao.findAll();

        // Assert
        List<Subject> matchingSubjects = subjects.stream().filter(s -> s.getId().equals(addedSubject.getId())).toList();
        assertEquals(1, matchingSubjects.size());
    }

    @Test
    @Commit
    void findById_returnsNewSubjectAfterAdd() {
        // Arrange
        Subject addedSubject = constructRandomSubject();

        // Act
        subjectDao.create(addedSubject);
        flushContext();

        Optional<Subject> subjectFromFind = subjectDao.findById(addedSubject.getId());

        // Assert
        assertTrue(subjectFromFind.isPresent());
        assertEquals(addedSubject.getId(), subjectFromFind.get().getId());
    }

    @Test
    @Commit
    void findById_returnsNoSubjectAfterAddAndDelete() {
        // Arrange
        Subject subject = constructRandomSubject();

        // Act
        subjectDao.create(subject);
        flushContext();

        subjectDao.deleteById(subject.getId());
        flushContext();

        Optional<Subject> subjectFromFind = subjectDao.findById(subject.getId());

        // Assert
        assertTrue(subjectFromFind.isEmpty());
    }
}
