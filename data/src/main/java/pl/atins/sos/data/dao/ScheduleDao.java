package pl.atins.sos.data.dao;

import pl.atins.sos.model.Schedule;

import java.util.Optional;

public interface ScheduleDao {

    Optional<Schedule> findByStudentId(Long id);

    void create(Schedule schedule);

    void deleteById(Long scheduleId, Long classId);
}
