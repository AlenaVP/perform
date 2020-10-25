package by.minsk.perform.repository;

import by.minsk.perform.model.User;

import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 25 Oct, 2020
 */

public interface UserRepository {

    User save(User user);

    boolean delete(long id);

    User get(long id);

    User getByEmail(String email);

    List<User> getAll();
}
