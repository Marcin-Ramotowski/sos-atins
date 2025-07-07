package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.atins.sos.model.Address;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressDaoTest {

    private AddressDaoImpl addressDao;
    private EntityManager em;

    @BeforeEach
    void setup() {
        em = mock(EntityManager.class);
        addressDao = new AddressDaoImpl();
        addressDao.em = em; // ręczne wstrzyknięcie EntityManagera
    }

    @Test
    void testFindAllByUserId() {
        long userId = 1L;
        TypedQuery<Address> mockQuery = mock(TypedQuery.class);
        List<Address> mockResult = List.of(new Address());

        when(em.createQuery(anyString(), eq(Address.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter("userId", userId)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(mockResult);

        List<Address> result = addressDao.findAllByUserId(userId);

        assertEquals(1, result.size());
        verify(em).createQuery(contains("a.user.id"), eq(Address.class));
    }

    @Test
    void testFindAllByCityId() {
        long cityId = 2L;
        TypedQuery<Address> mockQuery = mock(TypedQuery.class);
        when(em.createQuery(anyString(), eq(Address.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter("cityId", cityId)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of());

        List<Address> result = addressDao.findAllByCityId(cityId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindAllByCountryId() {
        long countryId = 3L;
        TypedQuery<Address> mockQuery = mock(TypedQuery.class);
        when(em.createQuery(anyString(), eq(Address.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter("countryId", countryId)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of());

        List<Address> result = addressDao.findAllByCountryId(countryId);

        assertNotNull(result);
        verify(mockQuery).setParameter("countryId", countryId);
    }

    @Test
    void testGetDefaultAddress_found() {
        long userId = 4L;
        Address mockAddress = new Address();
        TypedQuery<Address> mockQuery = mock(TypedQuery.class);
        when(em.createQuery(anyString(), eq(Address.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter("userId", userId)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of(mockAddress));

        Optional<Address> result = addressDao.getDefaultAddress(userId);

        assertTrue(result.isPresent());
        assertEquals(mockAddress, result.get());
    }

    @Test
    void testGetDefaultAddress_notFound() {
        long userId = 5L;
        TypedQuery<Address> mockQuery = mock(TypedQuery.class);
        when(em.createQuery(anyString(), eq(Address.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter("userId", userId)).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(List.of());

        Optional<Address> result = addressDao.getDefaultAddress(userId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSetDefaultAddress() {
        long userId = 1L;
        long addressId = 10L;

        Query updateQuery1 = mock(Query.class);
        Query updateQuery2 = mock(Query.class);

        when(em.createQuery(contains("SET a.isDefault = FALSE"))).thenReturn(updateQuery1);
        when(updateQuery1.setParameter("userId", userId)).thenReturn(updateQuery1);
        when(updateQuery1.executeUpdate()).thenReturn(1);

        when(em.createQuery(contains("SET a.isDefault = TRUE"))).thenReturn(updateQuery2);
        when(updateQuery2.setParameter("addressId", addressId)).thenReturn(updateQuery2);
        when(updateQuery2.executeUpdate()).thenReturn(1);

        addressDao.setDefaultAddress(userId, addressId);

        verify(updateQuery1).setParameter("userId", userId);
        verify(updateQuery2).setParameter("addressId", addressId);
        verify(updateQuery1).executeUpdate();
        verify(updateQuery2).executeUpdate();
    }
}
