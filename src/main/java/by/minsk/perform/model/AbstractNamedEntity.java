package by.minsk.perform.model;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 25 Oct, 2020
 */

public class AbstractNamedEntity extends AbstractBaseEntity {

    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() +
                '(' + name + ')';
    }
}
