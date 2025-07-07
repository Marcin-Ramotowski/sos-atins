package pl.atins.sos.data.dao;

import pl.atins.sos.model.City;

import java.util.List;
import java.util.Optional;

public interface CityDao extends CrudDao<City> {
    List<City> findByName(String name);
    Optional<City> findByCode(String code);
    List<City> findByCountryCode(String countryCode);
}
