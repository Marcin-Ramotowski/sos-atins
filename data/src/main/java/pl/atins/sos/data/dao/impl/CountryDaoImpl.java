package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.CountryDao;
import pl.atins.sos.model.Country;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryDaoImpl extends AbstractCrudDao<Country> implements CountryDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    protected Class<Country> getEntityClass() {
        return Country.class;
    }

    @Override
    public List<Country> findByName(String name) {
        String searchName = name.isBlank() ? "" : "%" + name.replaceAll("^%|%$", "") + "%";
        TypedQuery<Country> query = em.createQuery(
                "SELECT c FROM Country c WHERE c.name like :countryName",
                Country.class
        ).setParameter("countryName", searchName);
        return query.getResultList();
    }

    @Override
    public Optional<Country> findByCode(String code) {
        TypedQuery<Country> query = em.createQuery(
                "SELECT c FROM Country c WHERE c.code = :countryCode",
                Country.class
        ).setParameter("countryCode", code);
        List<Country> results = query.getResultList();
        return results.stream().findFirst();
    }
}