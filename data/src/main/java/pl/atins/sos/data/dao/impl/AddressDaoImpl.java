package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.AddressDao;
import pl.atins.sos.data.dao.util.QueryUtils;
import pl.atins.sos.model.Address;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressDaoImpl extends AbstractCrudDao<Address> implements AddressDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }

    @Override
    public List<Address> findAllByUserId(long userId) {
        TypedQuery<Address> query = em.createQuery(
                "SELECT a FROM Address a WHERE a.user.id = :userId",
                Address.class
        );
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Address> findAllByCityId(long cityId) {
        TypedQuery<Address> query = em.createQuery(
                "SELECT a FROM Address a WHERE a.city.id = :cityId",
                Address.class
        );
        query.setParameter("cityId", cityId);
        return query.getResultList();
    }

    @Override
    public List<Address> findAllByCountryId(long countryId) {
        TypedQuery<Address> query = em.createQuery(
                "SELECT a FROM Address a WHERE a.country.id = :countryId",
                Address.class
        );
        query.setParameter("countryId", countryId);
        return query.getResultList();
    }

    @Override
    public Optional<Address> getDefaultAddress(long userId) {
        TypedQuery<Address> query = em.createQuery(
                "SELECT a FROM Address a WHERE a.user.id = :userId AND a.isDefault = TRUE",
                Address.class
        ).setParameter("userId", userId);
        List<Address> results = query.getResultList();
        return Optional.ofNullable(results.getFirst());
    }

    @Override
    public void setDefaultAddress(long userId, long addressId) {
        QueryUtils.runDirectQuerySafely(em, () -> {
            // 1. Ustaw wszystkie adresy użytkownika jako niedomyślne
            em.createQuery("UPDATE Address a SET a.isDefault = FALSE WHERE a.user.id = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();

            // 2. Ustaw wybrany adres jako domyślny
            em.createQuery("UPDATE Address a SET a.isDefault = TRUE WHERE a.id = :addressId")
                    .setParameter("addressId", addressId)
                    .executeUpdate();
        });
    }
}