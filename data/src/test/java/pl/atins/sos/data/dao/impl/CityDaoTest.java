package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.atins.sos.model.City;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CityDaoTest {

    private CityDaoImpl cityDao;
    private EntityManager em;
    @SuppressWarnings("unchecked")
    private final TypedQuery<City> typedQuery = (TypedQuery<City>) mock(TypedQuery.class);

    @BeforeEach
    public void setUp() {
        em = mock(EntityManager.class);
        cityDao = new CityDaoImpl();
        cityDao.em = em; // ręczne wstrzyknięcie EntityManagera
    }

    @Test
    public void testFindByName_withValidName_returnsCities() {
        String name = "Warsaw";
        String expectedSearchName = "%Warsaw%";

        when(em.createQuery(contains("c.name like"), eq(City.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("cityName"), eq(expectedSearchName))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(new City()));

        List<City> result = cityDao.findByName(name);

        verify(em).createQuery(contains("c.name like"), eq(City.class));
        verify(typedQuery).setParameter("cityName", expectedSearchName);
        verify(typedQuery).getResultList();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testFindByName_withBlankName_returnsAll() {
        String name = "  ";

        when(em.createQuery(contains("c.name like"), eq(City.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("cityName"), eq(""))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of());

        List<City> result = cityDao.findByName(name);

        verify(em).createQuery(contains("c.name like"), eq(City.class));
        verify(typedQuery).setParameter("cityName", "");
        verify(typedQuery).getResultList();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByCode_withExistingCode_returnsCity() {
        String code = "ABC123";
        City city = new City();

        when(em.createQuery(contains("c.code ="), eq(City.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("cityCode"), eq(code))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(city));

        Optional<City> result = cityDao.findByCode(code);

        verify(em).createQuery(contains("c.code ="), eq(City.class));
        verify(typedQuery).setParameter("cityCode", code);
        verify(typedQuery).getResultList();

        assertTrue(result.isPresent());
        assertEquals(city, result.get());
    }

    @Test
    public void testFindByCode_withNoResults_returnsEmpty() {
        String code = "NOTFOUND";

        when(em.createQuery(contains("c.code ="), eq(City.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("cityCode"), eq(code))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of());

        Optional<City> result = cityDao.findByCode(code);

        verify(em).createQuery(contains("c.code ="), eq(City.class));
        verify(typedQuery).setParameter("cityCode", code);
        verify(typedQuery).getResultList();

        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByCountryCode_returnsCities() {
        String countryCode = "PL";

        when(em.createQuery(contains("c.country.code ="), eq(City.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("countryCode"), eq(countryCode))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(new City(), new City()));

        List<City> result = cityDao.findByCountryCode(countryCode);

        verify(em).createQuery(contains("c.country.code ="), eq(City.class));
        verify(typedQuery).setParameter("countryCode", countryCode);
        verify(typedQuery).getResultList();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
