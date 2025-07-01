package pl.atins.sos.data.dao;

import pl.atins.sos.data.dao.impl.ScheduleDaoImpl;
import pl.atins.sos.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleDao {

    List<Schedule> findByStudentId(Long id);

    void createSchedule(Schedule schedule);

    Optional<Schedule> updateSchedule(Schedule schedule);

    void deleteSchedule(Schedule schedule);
}
