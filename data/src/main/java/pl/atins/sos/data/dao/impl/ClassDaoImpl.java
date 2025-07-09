package pl.atins.sos.data.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.ClassDao;
import pl.atins.sos.model.UniversityClass;

import java.util.List;

@Repository
@Transactional
public class ClassDaoImpl extends AbstractCrudDao<UniversityClass> implements ClassDao {

    @Override
    public List<UniversityClass> findBySubjectId(long subjectId) {
        TypedQuery<UniversityClass> query = em.createQuery("FROM UniversityClass class where class.subject.id = :id", UniversityClass.class);
        query.setParameter("id", subjectId);
        return query.getResultList();
    }

    @Override
    protected Class<UniversityClass> getEntityClass() {
        return UniversityClass.class;
    }
}
