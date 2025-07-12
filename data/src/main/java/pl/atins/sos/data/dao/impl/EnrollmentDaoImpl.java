package pl.atins.sos.data.dao.impl;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import pl.atins.sos.data.dao.EnrollmentDao;
import pl.atins.sos.data.dao.util.QueryUtils;
import pl.atins.sos.model.Enrollment;

import java.util.List;

@Component
public class EnrollmentDaoImpl extends AbstractCrudDao<Enrollment> implements EnrollmentDao {

    @Override
    public void unregisterStudentFromSubject(long studentId, long subjectId) {
        QueryUtils.runDirectQuerySafely(em, () -> {
            Query query = em.createQuery("DELETE FROM Enrollment e"
                    + " WHERE e.subject.id = :subjectId AND e.student.id = :studentId");
            query.setParameter("subjectId", subjectId);
            query.setParameter("studentId", studentId);
            query.executeUpdate();
        });
    }

    @Override
    public List<Enrollment> findByStudentId(long studentId) {
        TypedQuery<Enrollment> query = em.createQuery("FROM Enrollment e where e.student.id = :id", Enrollment.class);
        query.setParameter("id", studentId);
        return query.getResultList();
    }

    @Override
    protected Class<Enrollment> getEntityClass() {
        return Enrollment.class;
    }
}
