package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.Transcript;

import java.util.List;

@Repository
public class TranscriptDao {

    @PersistenceContext
    private EntityManager em;

    public List<Transcript> findAll() {
        Query query = em.createQuery("from Transcript");
        return (List<Transcript>) query.getResultList();
    }
}
