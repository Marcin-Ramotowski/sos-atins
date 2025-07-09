package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.LoginDao;
import pl.atins.sos.model.Login;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@Transactional
public class LoginDaoImpl implements LoginDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void create(Login login) {
        login.setCreatedOn(OffsetDateTime.now());
        login.setLastUpdatedOn(OffsetDateTime.now());
        em.persist(login);
    }

    @Override
    public List<Login> findByUserId(long userId) {
        TypedQuery<Login> query = em.createQuery("SELECT l FROM Login l WHERE l.user.id = :user_id", Login.class);
        query.setParameter("user_id", userId);
        return (List<Login>) query.getResultList();
    }
}
