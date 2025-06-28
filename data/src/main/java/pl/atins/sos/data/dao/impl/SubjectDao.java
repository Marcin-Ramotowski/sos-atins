package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import pl.atins.sos.model.Subject;

import java.util.List;

@Component
public class SubjectDao {

    @PersistenceContext
    private EntityManager em;

    public List<Subject> loadSubjects() {
        Query query = em.createQuery("from Subject");
        return (List<Subject>) query.getResultList();
    }
}
