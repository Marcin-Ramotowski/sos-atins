package pl.atins.sos.data.dao.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pl.atins.sos.data.dao.CrudDao;
import pl.atins.sos.model.BaseEntity;

import java.time.OffsetDateTime;
import java.util.Optional;

public abstract class AbstractCrudDao<T extends BaseEntity> implements CrudDao<T> {

    @PersistenceContext
    private EntityManager em;

    protected abstract Class<T> getEntityClass();

    @Override
    public Optional<T> findById(long id) {
        return Optional.of(em.find(getEntityClass(), id));
    }

    @Override
    public void create(T entity) {
        entity.setCreatedOn(OffsetDateTime.now());
        entity.setLastUpdatedOn(OffsetDateTime.now());
        em.persist(entity);
    }

    @Override
    public Optional<T> update(T entity) {
        entity.setLastUpdatedOn(OffsetDateTime.now());
        return Optional.of(em.merge(entity));
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }
}
