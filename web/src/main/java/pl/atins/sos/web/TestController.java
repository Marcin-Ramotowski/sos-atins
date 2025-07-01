package pl.atins.sos.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.dao.impl.ClassDao;
import pl.atins.sos.data.dao.impl.DepartmentDaoImpl;
import pl.atins.sos.data.dao.impl.EnrollmentDao;
import pl.atins.sos.data.dao.impl.GradeDaoImpl;
import pl.atins.sos.data.dao.impl.ScheduleDaoImpl;
import pl.atins.sos.data.dao.impl.SubjectDao;
import pl.atins.sos.data.dao.impl.TranscriptDaoImpl;
import pl.atins.sos.data.dao.impl.UserDao;
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
    private final ScheduleDaoImpl scheduleDaoImpl;
    private final GradeDaoImpl gradeDaoImpl;
    private final TranscriptDaoImpl transcriptDaoImpl;
    private final DepartmentDaoImpl departmentDaoImpl;

    public TestController(UserDao userDao, ClassDao classDao, EnrollmentDao enrollmentDao, SubjectDao subjectDao, ScheduleDaoImpl scheduleDaoImpl, GradeDaoImpl gradeDaoImpl, TranscriptDaoImpl transcriptDaoImpl, DepartmentDaoImpl departmentDaoImpl) {
        this.userDao = userDao;
        this.classDao = classDao;
        this.enrollmentDao = enrollmentDao;
        this.subjectDao = subjectDao;
        this.scheduleDaoImpl = scheduleDaoImpl;
        this.gradeDaoImpl = gradeDaoImpl;
        this.transcriptDaoImpl = transcriptDaoImpl;
        this.departmentDaoImpl = departmentDaoImpl;
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
        String schedules = scheduleDaoImpl.findByStudentId(1L).stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "Schedules:" + HTML_NEW_LINE + schedules;
    }
    @GetMapping("/grade")
    public String getGrades() {
        String schedules = gradeDaoImpl.findById(1L).stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "grades:" + HTML_NEW_LINE + schedules;
    }
    @GetMapping("/department")
    public String getDepartments() {
        String schedules = departmentDaoImpl.findById(1L).stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "Departments:" + HTML_NEW_LINE + schedules;
    }
    @GetMapping("/transcript")
    public String getTranscripts() {
        String schedules = transcriptDaoImpl.findById(1L).stream()
                .map(Object::toString)
                .collect(Collectors.joining(HTML_NEW_LINE));
        return "transcripts:" + HTML_NEW_LINE + schedules;
    }

    private static final String HTML_NEW_LINE = "<br/>";
}
