package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.City;

import java.util.List;

@Repository
public class CityDao {

    @PersistenceContext
    private EntityManager em;

    public List<City> findAll() {
        Query query = em.createQuery("from City");
        return (List<City>) query.getResultList();
    }
}