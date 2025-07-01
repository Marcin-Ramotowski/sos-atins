package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.ScheduleDao;
import pl.atins.sos.model.Schedule;

import java.util.List;
import java.util.Optional;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Schedule> findByStudentId(Long id) {
        Query query = em.createQuery("FROM Schedule s where s.studentId=:studentId");
        query.setParameter("studentId", id);
        return query.getResultList();
    }

    @Override
    public void createSchedule(Schedule schedule) {
        em.persist(schedule);
    }

    @Override
    public Optional<Schedule> updateSchedule(Schedule schedule) {
        return Optional.of(em.merge(schedule));
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        em.remove(schedule);
    }
}