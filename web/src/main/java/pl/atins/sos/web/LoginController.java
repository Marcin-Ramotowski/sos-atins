package pl.atins.sos.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atins.sos.data.dao.LoginDao;
import pl.atins.sos.model.Login;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoginController {

    private final LoginDao loginDao;

    public LoginController(LoginDao loginDao) {
        this.loginDao = Objects.requireNonNull(loginDao);
    }

    @PostMapping("/internal/logins")
    public void create(@RequestBody Login login) {
        loginDao.create(login);
    }

    @GetMapping("/internal/users/{userId}/logins")
    public List<Login> findByUserId(@PathVariable("userId") long userId) {
        return loginDao.findByUserId(userId);
    }
}
