package pl.atins.sos.data.dao;

import pl.atins.sos.model.BaseEntity;

import java.util.Optional;
import java.util.function.Consumer;

public interface CrudDao<T extends BaseEntity> {
    Optional<T> findById(long id);

    void create(T entity);

    Optional<T> update(T entity);

    Optional<T> updateById(long id, Consumer<T> updater);

    void delete(T entity);

    /**
     * Delete entity directly by identifier
     * @param id id of the object to delete
     */
    void deleteById(long id);
}
