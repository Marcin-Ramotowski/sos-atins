package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.ScheduleDao;
import pl.atins.sos.model.Schedule;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ScheduleDaoImpl implements ScheduleDao {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public Optional<Schedule> findByStudentId(Long id) {
        TypedQuery<Schedule> query = em.createQuery("FROM Schedule s where s.student.id=:studentId", Schedule.class);
        query.setParameter("studentId", id);
        List<Schedule> singleResult = query.getResultList();
        return singleResult.isEmpty() ? Optional.empty() : Optional.of(singleResult.getFirst());
    }

    @Override
    public void create(Schedule schedule) {
        em.persist(schedule);
        schedule.setStudent(schedule.getStudent());
        schedule.setUniversityClass(schedule.getUniversityClass());
    }

    @Override
    public void deleteById(Long scheduleId, Long classId) {
        Optional<Schedule> schedule = findByStudentId(scheduleId);
        schedule.ifPresent(value -> em.remove(value));
    }
}