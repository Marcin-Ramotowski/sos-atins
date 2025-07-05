package pl.atins.sos.data.dao.impl;

import org.springframework.stereotype.Component;
import pl.atins.sos.data.dao.EnrollmentDao;
import pl.atins.sos.model.Enrollment;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class EnrollmentDaoImpl extends AbstractCrudDao<Enrollment> implements EnrollmentDao {

    @Override
    public Optional<Enrollment> registerStudentForSubject(long studentId, long subjectId) {
        return Optional.empty(); // TODO
    }

    @Override
    public void unregisterStudentFromSubject(long studentId, long subjectId) {
        // TODO
    }

    @Override
    public List<Enrollment> getEnrollmentsForStudent(long studentId) {
        return new LinkedList<>(); // TODO
    }

    @Override
    protected Class<Enrollment> getEntityClass() {
        return Enrollment.class;
    }
}
