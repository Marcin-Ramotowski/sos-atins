package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.Country;

import java.util.List;

@Repository
public class CountryDao {

    @PersistenceContext
    private EntityManager em;

    public List<Country> findAll() {
        Query query = em.createQuery("from Country");
        return (List<Country>) query.getResultList();
    }
}