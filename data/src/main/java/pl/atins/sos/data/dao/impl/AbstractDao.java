package pl.atins.sos.data.dao.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public abstract class AbstractDao<T> {

    @PersistenceContext
    private EntityManager em;

    protected abstract Class<T> getEntityCLass();
    
    Optional<T> findById(Long id){
        return Optional.of(em.find(getEntityCLass(), id));
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public Optional<T> update(T entity) {
        return Optional.of(em.merge(entity));
    }
    
    public void delete(T entity) {
        em.remove(entity);
    }
}
