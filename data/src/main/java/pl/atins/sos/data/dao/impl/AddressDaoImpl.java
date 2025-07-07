package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.AddressDao;
import pl.atins.sos.model.Address;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
public class AddressDaoImpl extends AbstractCrudDao implements AddressDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }



    @Override
    public Optional<Address> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void create(Address entity) {

    }

    @Override
    public Optional<Address> update(Address entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Address> updateById(long id, Consumer updater) {
        return Optional.empty();
    }

    @Override
    public void delete(Address entity) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<Address> findAllByUserId(long userId) {
        return List.of();
    }

    @Override
    public List<Address> findAllByCity(String city) {
        return List.of();
    }

    @Override
    public List<Address> findAllByCountry(String country) {
        return List.of();
    }

    @Override
    public Optional<Address> getDefaultAddress(long userId) {
        return Optional.empty();
    }

    @Override
    public void setDefaultAddress(long userId, long addressId) {

    }
}