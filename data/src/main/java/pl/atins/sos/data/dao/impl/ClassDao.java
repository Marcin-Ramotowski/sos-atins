package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.CrudDao;
import pl.atins.sos.model.UniversityClass;

import java.util.List;

@Repository//TODO - version only for test - needs to be completed
public class ClassDao extends AbstractCrudDao<UniversityClass> implements CrudDao<UniversityClass> {

    @PersistenceContext
    private EntityManager em;

    public List<UniversityClass> loadClasses() {
        Query query = em.createQuery("from UniversityClass");
        return (List<UniversityClass>) query.getResultList();
    }

    @Override
    protected Class<UniversityClass> getEntityClass() {
        return UniversityClass.class;
    }
}
