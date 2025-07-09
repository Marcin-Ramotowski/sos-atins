package pl.atins.sos.data.dao.impl;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.GradeDao;
import pl.atins.sos.model.Enrollment;
import pl.atins.sos.model.Grade;

import java.util.List;

@Repository
public class GradeDaoImpl extends AbstractCrudDao<Grade> implements GradeDao {


    @Override
    protected Class<Grade> getEntityClass() {
        return Grade.class;
    }

    @Override
    public List<Grade> findByStudentId(Long studentId) {
        TypedQuery<Grade> query = em.createQuery("FROM Grade g JOIN Transcript t on g.transcript = t WHERE t.student.id " +
                "= :studentId", Grade.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }
}
