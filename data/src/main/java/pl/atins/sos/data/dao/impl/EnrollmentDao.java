package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import pl.atins.sos.model.Enrollment;

import java.util.List;

@Component
public class EnrollmentDao {

    @PersistenceContext
    private EntityManager em;

    public List<Enrollment> loadEnrollments() {
        Query query = em.createQuery("from Enrollment");
        return (List<Enrollment>) query.getResultList();
    }
}
