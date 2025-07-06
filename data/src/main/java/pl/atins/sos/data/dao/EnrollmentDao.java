package pl.atins.sos.data.dao;

import pl.atins.sos.model.Enrollment;
import pl.atins.sos.model.Student;
import pl.atins.sos.model.Subject;

import java.util.List;
import java.util.Optional;

public interface EnrollmentDao extends CrudDao<Enrollment> {

    void unregisterStudentFromSubject(long studentId, long subjectId);

    List<Enrollment> findByStudentId(long studentId);
}
