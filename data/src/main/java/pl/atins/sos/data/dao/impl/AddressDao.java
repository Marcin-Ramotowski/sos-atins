package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.Address;

import java.util.List;

@Repository
public class AddressDao {

    @PersistenceContext
    private EntityManager em;

    public List<Address> findAll() {
        Query query = em.createQuery("from Address");
        return (List<Address>) query.getResultList();
    }
}