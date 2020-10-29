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

public class Album extends AbstractNamedEntity {

    private int year;
    private List<String> tracks;

    public Album() {
    }

    public Album(String name) {
        this(null, name, Year.now().getValue(), Collections.emptyList());
    }

    public Album(String name, int year, String... tracks) {
        this(null, name, year, Arrays.asList(tracks));
    }

    public Album(Long id, String name, String... tracks) {
        this(id, name, Year.now().getValue(), Arrays.asList(tracks));
    }

    public Album(Long id, String name, int year, List<String> tracks) {
        super(id, name);
        this.year = year;
        this.tracks = tracks;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name=" + name +
                ", year=" + year +
                ", tracks=" + tracks +
                '}';
    }
}
