package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import pl.atins.sos.data.dao.CrudDao;
import pl.atins.sos.model.BaseEntity;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.function.Consumer;

@Transactional
public abstract class AbstractCrudDao<T extends BaseEntity> implements CrudDao<T> {

    @PersistenceContext
    protected EntityManager em;

    protected abstract Class<T> getEntityClass();

    private final String entityName = getEntityClass().getSimpleName();

    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(em.find(getEntityClass(), id));
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
    public Optional<T> updateById(long id, Consumer<T> updater) {
        return findById(id).map(entity -> {
            entity.setLastUpdatedOn(OffsetDateTime.now());
            updater.accept(entity);
            return entity;
        });
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public void deleteById(long id) {
        // entityName is always a class name, hence injection is almost certainly impossible here
        Query query = em.createQuery("DELETE FROM " + entityName + " e WHERE e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
