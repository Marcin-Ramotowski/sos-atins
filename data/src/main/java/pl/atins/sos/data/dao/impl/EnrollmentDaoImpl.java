package pl.atins.sos.data.dao.impl;

import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import pl.atins.sos.data.dao.EnrollmentDao;
import pl.atins.sos.model.Enrollment;
import pl.atins.sos.model.Student;
import pl.atins.sos.model.Subject;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class EnrollmentDaoImpl extends AbstractCrudDao<Enrollment> implements EnrollmentDao {

    @Override
    public void unregisterStudentFromSubject(long studentId, long subjectId) {
        Query query = em.createQuery("DELETE FROM " + getEntityName() + " e"
                + " WHERE e.subject.id = :subjectId AND e.student.id = :studentId");
        query.setParameter("subjectId", subjectId);
        query.setParameter("studentId", studentId);
        query.executeUpdate();
    }

    @Override
    public List<Enrollment> findByStudentId(long studentId) {
        Query query = em.createQuery("FROM " + getEntityName() + " e where e.student.id = :id");
        query.setParameter("id", studentId);
        return query.getResultList();
    }

    @Override
    protected Class<Enrollment> getEntityClass() {
        return Enrollment.class;
    }
}
