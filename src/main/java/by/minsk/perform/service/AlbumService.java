package by.minsk.perform.service;

import by.minsk.perform.model.Album;
import by.minsk.perform.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static by.minsk.perform.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 27 Oct, 2020
 */

@Service
public class AlbumService {

    private final AlbumRepository repository;

    public AlbumService(AlbumRepository repository) {
        this.repository = repository;
    }

    public Album create(Album album, long performerId) {
        Assert.notNull(album, "album must not be null");
        return repository.save(album, performerId);
    }

    public void update(Album album, long performerId) {
        Assert.notNull(album, "(update) album must not be null");
        checkNotFoundWithId(repository.save(album, performerId), album.id());
    }

    public void delete(long id, long performerId) {
        checkNotFoundWithId(repository.delete(id, performerId), id);
    }

    public Album get(long id, long performerId) {
        return checkNotFoundWithId(repository.get(id, performerId), id);
    }

    public List<Album> getAll(long performerId) {
        return repository.getAll(performerId);
    }
}
