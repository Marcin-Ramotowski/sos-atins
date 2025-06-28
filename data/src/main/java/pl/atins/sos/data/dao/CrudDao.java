package pl.atins.sos.data.dao;

import pl.atins.sos.model.BaseEntity;

import java.util.Optional;

public interface CrudDao<T extends BaseEntity> {
    Optional<T> findById(long id);

    void create(T entity);

    Optional<T> update(T entity);

    void delete(T entity);
}
