package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.UserDao;
import pl.atins.sos.data.dao.util.QueryUtils;
import pl.atins.sos.model.User;

import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void activateById(long id) {
        QueryUtils.runDirectQuerySafely(em, () -> {
            Query query = em.createQuery("UPDATE User u SET u.active = true WHERE u.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        });
    }

    @Override
    public Optional<User> findByLogin(String login) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
