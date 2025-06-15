package pl.atins.sos.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.ClassDao;
import pl.atins.sos.data.UserDao;
import pl.atins.sos.model.UniversityClass;
import pl.atins.sos.model.User;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserDao userDao;
    private final ClassDao classDao;

    public TestController(UserDao userDao, ClassDao classDao) {
        this.userDao = Objects.requireNonNull(userDao);
        this.classDao = Objects.requireNonNull(classDao);
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
                .collect(Collectors.joining("\n"));
        return "Classes: \n" + classes;
    }
}
