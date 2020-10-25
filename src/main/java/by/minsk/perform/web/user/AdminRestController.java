package by.minsk.perform.web.user;

import by.minsk.perform.model.User;

import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 26 Oct, 2020
 */

public class AdminRestController extends AbstractUserController {

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(long id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    public void update(User user, long id) {
        super.update(user, id);
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }
}
