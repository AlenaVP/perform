package by.minsk.perform.model;

import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.1
 * @since 28 Oct, 2020
 */

public class Performer extends AbstractNamedEntity {

    private String origin;
    private int year;
    private List<Album> albums;

    public Performer() {
    }

    public Performer(String name, String origin) {
        this(null, name, origin, Year.now().getValue(), Collections.emptyList());
    }

    public Performer(String name, String origin, int year, Album... albums) {
        this(null, name, origin, year, Arrays.asList(albums));
    }

    public Performer(Long id, String name, String origin, int year, Album... albums) {
        this(id, name, origin, year, Arrays.asList(albums));
    }

    public Performer(Long id, String name, String origin, int year, List<Album> albums) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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
                ", name=" + name +
                ", origin=" + origin +
                ", year=" + year +
                ", albums=" + albums +
                '}';
    }
}
