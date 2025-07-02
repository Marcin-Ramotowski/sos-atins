package pl.atins.sos.data.dao.impl;

import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.GradeDao;
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
        Query query = em.createQuery("FROM Grade g JOIN Transcript t on g.transcript = t WHERE t.student.id " +
                "= :studentId");
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }
}
