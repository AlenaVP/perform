package by.minsk.perform.web.user;

import by.minsk.perform.model.User;
import by.minsk.perform.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.minsk.perform.util.ValidationUtil.assureIdConsistent;
import static by.minsk.perform.util.ValidationUtil.checkNew;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 26 Oct, 2020
 */

public abstract class AbstractUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void update(User user, long id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public void delete(long id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public User get(long id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }
    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}