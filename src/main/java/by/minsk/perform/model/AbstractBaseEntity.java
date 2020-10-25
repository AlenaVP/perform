package by.minsk.perform.model;

import org.springframework.util.Assert;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 25 Oct, 2020
 */

public abstract class AbstractBaseEntity {

    public static final long START_SEQ = 10_000;

    protected Long id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long id() {
        Assert.notNull(id, "Entity must has id");
        return id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.intValue();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                ":" + id;
    }
}
