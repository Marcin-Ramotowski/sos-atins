package pl.atins.sos.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.*;
import pl.atins.sos.model.Enrollment;
import pl.atins.sos.model.Subject;
import pl.atins.sos.model.UniversityClass;
import pl.atins.sos.model.User;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserDao userDao;
    private final ClassDao classDao;
    private final EnrollmentDao enrollmentDao;
    private final SubjectDao subjectDao;
    private final ScheduleDao scheduleDao;
    private final GradeDao gradeDao;
    private final TranscriptDao transcriptDao;
    private final DepartmentDao departmentDao;

    public TestController(UserDao userDao, ClassDao classDao, EnrollmentDao enrollmentDao, SubjectDao subjectDao, ScheduleDao scheduleDao, GradeDao gradeDao, TranscriptDao transcriptDao, DepartmentDao departmentDao) {
        this.userDao = userDao;
        this.classDao = classDao;
        this.enrollmentDao = enrollmentDao;
        this.subjectDao = subjectDao;
        this.scheduleDao = scheduleDao;
        this.gradeDao = gradeDao;
        this.transcriptDao = transcriptDao;
        this.departmentDao = departmentDao;
    }

    @GetMapping
    public String get() {
        String usernames = userDao.loadUsers().stream()
                .map(User::getFirstName)
                .collect(Collectors.joining(" "));
        return "Users: " + usernames;
    }

    @GetMapping("/class")
    public String getClasses() {
        String classes = classDao.loadClasses().stream()
                .map(UniversityClass::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "Classes: <br/>" + classes;
    }

    @GetMapping("/enrollment")
    public String getEnrollments() {
        String enrollments = enrollmentDao.loadEnrollments().stream()
                .map(Enrollment::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "Enrollments:" + HTML_NEW_LINE + enrollments;
    }

    @GetMapping("/subject")
    public String getSubjects() {
        String subjects = subjectDao.loadSubjects().stream()
                .map(Subject::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "Subjects:" + HTML_NEW_LINE + subjects;
    }
    @GetMapping("/schedule")
    public String getSchedules() {
        String schedules = scheduleDao.findAll().stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "Schedules:" + HTML_NEW_LINE + schedules;
    }
    @GetMapping("/grade")
    public String getGrades() {
        String schedules = gradeDao.findAll().stream()
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
        String schedules = transcriptDao.findAll().stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "transcripts:" + HTML_NEW_LINE + schedules;
    }

    private static final String HTML_NEW_LINE = "<br/>";
}
