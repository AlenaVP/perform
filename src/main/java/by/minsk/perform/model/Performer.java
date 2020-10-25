package by.minsk.perform.model;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 25 Oct, 2020
 */

public class Performer extends AbstractNamedEntity {

    private String origin;
    private Year year;
    private List<Album> albums;

    public Performer() {
    }

    public Performer(long id, String name, String origin, Year year, Album... albums) {
        this(id, name, origin, Year.now(), Arrays.asList(albums));
    }

    public Performer(long id, String name, String origin, Year year, List<Album> albums) {
        super(id, name);
        this.origin = origin;
        this.year = year;
        this.albums = albums;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", name='" + name +
                ", origin='" + origin +
                ", year=" + year +
                ", albums=" + albums +
                '}';
    }
}
