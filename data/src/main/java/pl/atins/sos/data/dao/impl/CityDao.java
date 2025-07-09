package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.City;

import java.util.List;

@Repository
public class CityDao {

    @PersistenceContext
    protected EntityManager em;

    public List<City> findAll() {
        TypedQuery<City> query = em.createQuery("from City", City.class);
        return query.getResultList();
    }
}