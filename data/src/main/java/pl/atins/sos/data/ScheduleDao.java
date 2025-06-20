package pl.atins.sos.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.model.Schedule;

import java.util.List;

@Repository
public class ScheduleDao {
    @PersistenceContext
    private EntityManager em;

    public List<Schedule> findAll() {
        Query query = em.createQuery("from Schedule");
        return (List<Schedule>) query.getResultList();
    }
}