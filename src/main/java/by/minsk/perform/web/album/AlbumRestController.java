package by.minsk.perform.web.album;

import by.minsk.perform.model.Album;
import by.minsk.perform.service.AlbumService;
import by.minsk.perform.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

import static by.minsk.perform.util.ValidationUtil.assureIdConsistent;
import static by.minsk.perform.util.ValidationUtil.checkNew;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 27 Oct, 2020
 */

@Controller
public class AlbumRestController {

    private static final Logger log = LoggerFactory.getLogger(AlbumRestController.class);

    private final AlbumService service;

    public AlbumRestController(AlbumService service) {
        this.service = service;
    }

    public Album create(Album album) {
        long performerId = SecurityUtil.authPerformerId();
        checkNew(album);
        log.info("create {} for performer {}", album, performerId);
        return service.create(album, performerId);
    }

    public void update(Album album, long id) {
        long performerId = SecurityUtil.authPerformerId();
        assureIdConsistent(album, id);
        log.info("update {} for performer {}", album, performerId);
        service.update(album, performerId);
    }

    public void delete(long id) {
        long performerId = SecurityUtil.authPerformerId();
        log.info("delete album {} for performer {}", id, performerId);
        service.delete(id, performerId);
    }

    public Album get(long id) {
        long performerId = SecurityUtil.authPerformerId();
        log.info("get album {} for performer {}", id, performerId);
        return service.get(id, performerId);
    }

    public List<Album> getAll() {
        long performerId = SecurityUtil.authPerformerId();
        log.info("getAll for performer {}", performerId);
        return service.getAll(performerId);
    }
}
