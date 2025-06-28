package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import pl.atins.sos.model.UniversityClass;

import java.util.List;

@Component
public class ClassDao {

    @PersistenceContext
    private EntityManager em;

    public List<UniversityClass> loadClasses() {
        Query query = em.createQuery("from UniversityClass");
        return (List<UniversityClass>) query.getResultList();
    }
}
