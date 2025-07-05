package pl.atins.sos.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.dao.DepartmentDao;
import pl.atins.sos.data.dao.GradeDao;
import pl.atins.sos.data.dao.ScheduleDao;
import pl.atins.sos.data.dao.TranscriptDao;
import pl.atins.sos.data.dao.impl.ClassDao;
import pl.atins.sos.data.dao.impl.EnrollmentDao;
import pl.atins.sos.data.dao.impl.SubjectDao;
import pl.atins.sos.model.Enrollment;
import pl.atins.sos.model.Subject;
import pl.atins.sos.model.UniversityClass;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    private final ScheduleDao scheduleDao;
    private final GradeDao gradeDao;
    private final TranscriptDao transcriptDao;
    private final DepartmentDao departmentDao;

    public TestController(ScheduleDao scheduleDao, GradeDao gradeDao, TranscriptDao transcriptDao, DepartmentDao departmentDao) {
        this.scheduleDao = scheduleDao;
        this.gradeDao = gradeDao;
        this.transcriptDao = transcriptDao;
        this.departmentDao = departmentDao;
    }

    @GetMapping("/schedule")
    public String getSchedules() {
        String schedules = scheduleDao.findByStudentId(1L).stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "Schedules:" + HTML_NEW_LINE + schedules;
    }
    @GetMapping("/grade")
    public String getGrades() {
        String schedules = gradeDao.findById(1L).stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "grades:" + HTML_NEW_LINE + schedules;
    }
    @GetMapping("/department")
    public String getDepartments() {
        String schedules = departmentDao.findAll().stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "Departments:" + HTML_NEW_LINE + schedules;
    }
    @GetMapping("/transcript")
    public String getTranscripts() {
        String schedules = transcriptDao.findByStudentId(1L).stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "transcripts:" + HTML_NEW_LINE + schedules;
    }

    private static final String HTML_NEW_LINE = "<br/>";
}
