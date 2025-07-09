package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.Address;

import java.util.List;

@Repository
public class AddressDao {

    @PersistenceContext
    protected EntityManager em;

    public List<Address> findAll() {
        TypedQuery<Address> query = em.createQuery("from Address", Address.class);
        return query.getResultList();
    }
}