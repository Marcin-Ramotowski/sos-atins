package pl.atins.sos.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.dao.UserDao;
import pl.atins.sos.model.User;

import java.util.Objects;

@RestController
@RequestMapping(path = "/internal/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = Objects.requireNonNull(userDao);
    }

    @PostMapping("/{userId}/activate")
    public void activateById(@PathVariable("userId") long userId) {
        userDao.activateById(userId);
    }

    @GetMapping("/logins/{login}")
    public User findByLogin(@PathVariable("login") String login) {
        return userDao.findByLogin(login).get();
    }
}
