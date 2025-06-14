package pl.atins.sos.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.UserDao;
import pl.atins.sos.model.User;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserDao userDao;

    public TestController(UserDao userDao) {
        this.userDao = Objects.requireNonNull(userDao);
    }

    @GetMapping
    public String get() {
        String usernames = userDao.loadUsers().stream()
                .map(User::getFirstName)
                .collect(Collectors.joining(" "));
        return "Users: " + usernames;
    }
}
