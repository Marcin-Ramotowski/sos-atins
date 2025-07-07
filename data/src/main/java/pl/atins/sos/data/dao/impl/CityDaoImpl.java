package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.CityDao;
import pl.atins.sos.model.City;

import java.util.List;
import java.util.Optional;

@Repository
public class CityDaoImpl extends AbstractCrudDao<City> implements CityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    public List<City> findByName(String name) {
        String searchName = name.isBlank() ? "" : "%" + name.replaceAll("^%|%$", "") + "%";
        TypedQuery<City> query = em.createQuery(
                "SELECT c FROM City c WHERE c.name like :cityName",
                City.class
        ).setParameter("cityName", searchName);
        return query.getResultList();
    }

    @Override
    public Optional<City> findByCode(String code) {
        TypedQuery<City> query = em.createQuery(
                "SELECT c FROM City c WHERE c.code = :cityCode",
                City.class
        ).setParameter("cityCode", code);
        List<City> results = query.getResultList();
        return results.stream().findFirst();
    }

    @Override
    public List<City> findByCountryCode(String countryCode) {
        TypedQuery<City> query = em.createQuery(
                "SELECT c FROM City c WHERE c.country.code = :countryCode",
                City.class
        ).setParameter("countryCode", countryCode);
        return query.getResultList();
    }
}