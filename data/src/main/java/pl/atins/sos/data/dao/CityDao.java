package pl.atins.sos.data.dao;

import pl.atins.sos.model.Address;
import pl.atins.sos.model.City;

import java.util.List;
import java.util.Optional;

public interface CityDao extends CrudDao<City> {
    Optional<City> findByName(String name);
    Optional<City> findByCode(String code);
    List<City> findByCountry(String countryCode);
}
