package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.Country;

import java.util.List;

@Repository
public class CountryDao {

    @PersistenceContext
    protected EntityManager em;

    public List<Country> findAll() {
        TypedQuery<Country> query = em.createQuery("from Country", Country.class);
        return query.getResultList();
    }
}