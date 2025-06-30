package pl.atins.sos.data.dao;

import pl.atins.sos.model.BaseEntity;

import java.util.Optional;

public interface CrudDao<T extends BaseEntity> {
    Optional<T> findById(long id);

    void create(T entity);

    Optional<T> update(T entity);

    void delete(T entity);

    /**
     * Find and delete as a single transaction
     * @param id id of the object to delete
     */
    void deleteById(long id);
}
