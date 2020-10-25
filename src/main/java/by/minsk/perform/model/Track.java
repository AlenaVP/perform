package by.minsk.perform.model;

import java.time.LocalDateTime;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 25 Oct, 2020
 */

public class Track extends AbstractNamedEntity {

    private LocalDateTime created;

    public Track() {
    }

    public Track(long id, String name) {
        this(id, name, LocalDateTime.now());
    }

    public Track(long id, String name, LocalDateTime created) {
        super(id, name);
        this.created = created;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name +
                ", created=" + created +
                '}';
    }
}
