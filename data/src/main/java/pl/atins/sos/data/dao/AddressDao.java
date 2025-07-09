package pl.atins.sos.data.dao;

import pl.atins.sos.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao extends CrudDao<Address> {
    List<Address> findAllByUserId(long userId);
    List<Address> findAllByCityId(long cityId);
    List<Address> findAllByCountryId(long countryId);
    Optional<Address> getDefaultAddress(long userId);
    void setDefaultAddress(long userId, long addressId);
}
