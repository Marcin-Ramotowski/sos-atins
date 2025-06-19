package pl.atins.sos.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.Grade;

import java.util.List;

@Repository
public class GradeDao {

    @PersistenceContext
    private EntityManager em;

    public List<Grade> findAll() {
        Query query = em.createQuery("from Grade");
        return (List<Grade>) query.getResultList();
    }
}
