package by.minsk.perform.model;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 25 Oct, 2020
 */

public class Album extends AbstractNamedEntity {

    private Year year;
    List<Track> tracks;

    public Album() {
    }

    public Album(long id, String name, Track... tracks) {
        this(id, name, Year.now(), Arrays.asList(tracks));
    }

    public Album(long id, String name, Year year, List<Track> tracks) {
        super(id, name);
        this.year = year;
        this.tracks = tracks;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name +
                ", year=" + year +
                ", tracks=" + tracks +
                '}';
    }
}
