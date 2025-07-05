package pl.atins.sos.data.dao.impl;

import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.ClassDao;
import pl.atins.sos.model.Transcript;
import pl.atins.sos.model.UniversityClass;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ClassDaoImpl extends AbstractCrudDao<UniversityClass> implements ClassDao {

    @Override
    public List<UniversityClass> findBySubjectId(long subjectId) {
        Query query = em.createQuery("FROM " + getEntityName() + " class where class.subject.id = :id");
        query.setParameter("id", subjectId);
        return query.getResultList();
    }

    @Override
    protected Class<UniversityClass> getEntityClass() {
        return UniversityClass.class;
    }
}
