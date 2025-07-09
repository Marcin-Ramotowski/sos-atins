package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.atins.sos.model.Country;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CountryDaoTest {

    private CountryDaoImpl countryDao;
    private EntityManager em;
    @SuppressWarnings("unchecked")
    private final TypedQuery<Country> typedQuery = (TypedQuery<Country>) mock(TypedQuery.class);

    @BeforeEach
    public void setUp() {
        em = mock(EntityManager.class);
        countryDao = new CountryDaoImpl();
        countryDao.em = em; // ręczne wstrzyknięcie EntityManagera
    }

    @Test
    public void testFindByName_withValidName_returnsCountries() {
        String name = "Poland";
        String expectedSearchName = "%Poland%";

        when(em.createQuery(contains("c.name like"), eq(Country.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("countryName"), eq(expectedSearchName))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(new Country()));

        List<Country> result = countryDao.findByName(name);

        verify(em).createQuery(contains("c.name like"), eq(Country.class));
        verify(typedQuery).setParameter("countryName", expectedSearchName);
        verify(typedQuery).getResultList();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testFindByName_withBlankName_returnsAll() {
        String name = "  ";
        String expectedSearchName = "";

        when(em.createQuery(contains("c.name like"), eq(Country.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("countryName"), eq(expectedSearchName))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of());

        List<Country> result = countryDao.findByName(name);

        verify(em).createQuery(contains("c.name like"), eq(Country.class));
        verify(typedQuery).setParameter("countryName", expectedSearchName);
        verify(typedQuery).getResultList();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByCode_withExistingCode_returnsCountry() {
        String code = "PL";
        Country country = new Country();

        when(em.createQuery(contains("c.code ="), eq(Country.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("countryCode"), eq(code))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(country));

        Optional<Country> result = countryDao.findByCode(code);

        verify(em).createQuery(contains("c.code ="), eq(Country.class));
        verify(typedQuery).setParameter("countryCode", code);
        verify(typedQuery).getResultList();

        assertTrue(result.isPresent());
        assertEquals(country, result.get());
    }

    @Test
    public void testFindByCode_withNoResults_returnsEmpty() {
        String code = "XX";

        when(em.createQuery(contains("c.code ="), eq(Country.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("countryCode"), eq(code))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of());

        Optional<Country> result = countryDao.findByCode(code);

        verify(em).createQuery(contains("c.code ="), eq(Country.class));
        verify(typedQuery).setParameter("countryCode", code);
        verify(typedQuery).getResultList();

        assertFalse(result.isPresent());
    }
}
