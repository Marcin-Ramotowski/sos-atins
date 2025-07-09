package pl.atins.sos.data.dao;

import pl.atins.sos.model.City;
import pl.atins.sos.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDao extends CrudDao<Country> {
    List<Country> findByName(String name);
    Optional<Country> findByCode(String countryCode);
}
