package pl.atins.sos.data.dao;

import pl.atins.sos.model.User;

import java.util.Optional;

public interface UserDao {
    void activateById(long userId);

    Optional<User> findByLogin(String login);
}
