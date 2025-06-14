package pl.atins.sos.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import pl.atins.sos.model.User;

import java.util.List;

@Component
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public List<User> loadUsers() {
        Query query = em.createQuery("from User");
        return (List<User>) query.getResultList();
    }
}
