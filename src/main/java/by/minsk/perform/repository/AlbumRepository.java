package by.minsk.perform.repository;

import by.minsk.perform.model.Album;

import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 27 Oct, 2020
 */

public interface AlbumRepository {

    Album save(Album album, long performerId);

    boolean delete(long id, long performerId);

    Album get(long id, long performerId);

    List<Album> getAll(long performerId);
}
