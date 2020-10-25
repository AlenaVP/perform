package by.minsk.perform.model;

import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 25 Oct, 2020
 */

public class User extends AbstractNamedEntity {

    private String email;
    private String password;
    private boolean enabled;
    private LocalDateTime registered;
    private Set<Role> roles;

    public User() {
    }

    public User(long id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, LocalDateTime.now(), EnumSet.of(role, roles));
    }

    public User(long id, String name, String email, String password, boolean enabled, LocalDateTime registered, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email +
                ", name='" + name +
                ", password='" + password +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                '}';
    }
}
