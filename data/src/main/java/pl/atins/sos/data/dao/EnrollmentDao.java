package pl.atins.sos.data.dao;

import pl.atins.sos.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentDao extends CrudDao<Enrollment> {

    Optional<Enrollment> registerStudentForSubject(long studentId, long subjectId);
    void unregisterStudentFromSubject(long studentId, long subjectId);

    List<Enrollment> getEnrollmentsForStudent(long studentId);
}
