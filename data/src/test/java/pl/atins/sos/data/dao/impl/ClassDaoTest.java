package pl.atins.sos.data.dao.impl;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import pl.atins.sos.data.dao.ClassDao;
import pl.atins.sos.data.dao.SubjectDao;
import pl.atins.sos.model.Subject;
import pl.atins.sos.model.Teacher;
import pl.atins.sos.model.UniversityClass;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class ClassDaoTest extends BaseIntegrationTest {

    @Autowired
    private ClassDao classDao;

    @Autowired
    private SubjectDao subjectDao;

    @Test
    void findBySubjectId_returnsNoClassesForNewSubject() {
        // Arrange
        Subject subject = constructRandomSubject();

        // Act
        subjectDao.create(subject);
        flushContext();

        List<UniversityClass> classes = classDao.findBySubjectId(subject.getId());

        // Assert
        assertEquals(0, classes.size());
    }

    @Test
    @Commit
    void findBySubjectId_containsAddedClasses() {
        // Arrange
        Subject subject = constructAndPersistNewSubject();
        Teacher teacher = constructAndPersistNewTeacher();
        flushContext();

        UniversityClass addedClass1 = constructRandomClassForSubjectAndTeacher(subject, teacher);
        UniversityClass addedClass2 = constructRandomClassForSubjectAndTeacher(subject, teacher);

        // Act
        classDao.create(addedClass1);
        classDao.create(addedClass2);
        flushContext();

        List<UniversityClass> classes = classDao.findBySubjectId(subject.getId());

        // Assert
        assertEquals(2, classes.size());
        assertTrue(containsClassWithId(classes, addedClass1.getId()));
        assertTrue(containsClassWithId(classes, addedClass2.getId()));
    }

    private static boolean containsClassWithId(List<UniversityClass> list, long classId) {
        return list.stream().anyMatch(c -> c.getId().equals(classId));
    }
}
