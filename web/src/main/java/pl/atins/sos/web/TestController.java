package pl.atins.sos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.ClassDao;
import pl.atins.sos.data.EnrollmentDao;
import pl.atins.sos.data.SubjectDao;
import pl.atins.sos.data.UserDao;
import pl.atins.sos.model.Enrollment;
import pl.atins.sos.model.Subject;
import pl.atins.sos.model.UniversityClass;
import pl.atins.sos.model.User;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserDao userDao;
    private final ClassDao classDao;
    private final EnrollmentDao enrollmentDao;
    private final SubjectDao subjectDao;

    public TestController(UserDao userDao, ClassDao classDao, EnrollmentDao enrollmentDao, SubjectDao subjectDao) {
        this.userDao = Objects.requireNonNull(userDao);
        this.classDao = Objects.requireNonNull(classDao);
        this.enrollmentDao = Objects.requireNonNull(enrollmentDao);
        this.subjectDao = Objects.requireNonNull(subjectDao);
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

    private static final String HTML_NEW_LINE = "<br/>";
}
