package pl.atins.sos.data.dao;

import pl.atins.sos.model.Login;

import java.util.List;

public interface LoginDao {
    void create(Login login);

    List<Login> findByUserId(long userId);
}
