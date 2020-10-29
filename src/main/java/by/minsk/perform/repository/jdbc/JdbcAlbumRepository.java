package by.minsk.perform.repository.jdbc;

import by.minsk.perform.mapper.AlbumMapper;
import by.minsk.perform.model.Album;
import by.minsk.perform.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.1
 * @since 28 Oct, 2020
 */

@Repository
public class JdbcAlbumRepository implements AlbumRepository {

    private static RowMapper<Album> ROW_MAPPER = new AlbumMapper();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAlbumRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Album save(Album album, long performerId) {
        int row;
        if (album.isNew()) {
            PreparedStatementCreator preparedStatementCreator = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO albums (name, year, tracks, performer_id) VALUES (?, ?, ?, ?)"
                );
                preparedStatement.setString(1, album.getName());
                preparedStatement.setInt(2, album.getYear());
                preparedStatement.setArray(3, connection.createArrayOf("varchar",
                        album.getTracks().toArray()));
                preparedStatement.setLong(4, performerId);
                return preparedStatement;
            };
            KeyHolder keyHolder = new GeneratedKeyHolder();
            row = jdbcTemplate.update(preparedStatementCreator, keyHolder);
            album.setId((Long) keyHolder.getKey());
        } else {
            PreparedStatementCreator preparedStatementCreator = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE albums SET name = ?, year = ?, tracks = ? " +
                                "WHERE id = ? AND performer_id = ?"
                );
                preparedStatement.setString(1, album.getName());
                preparedStatement.setInt(2, album.getYear());
                preparedStatement.setArray(3, connection.createArrayOf("varchar",
                        album.getTracks().toArray()));
                preparedStatement.setLong(4, album.getId());
                preparedStatement.setLong(5, performerId);
                return preparedStatement;
            };
            row = jdbcTemplate.update(preparedStatementCreator);
        }
        return row == 0 ? null : album;
    }

    @Override
    public boolean delete(long id, long performerId) {
        return jdbcTemplate.update("DELETE FROM albums WHERE id = ? AND performer_id = ?", id, performerId) != 0;
    }

    @Override
    public Album get(long id, long performerId) {
        List<Album> albums = jdbcTemplate.query(
                "SELECT * FROM albums WHERE id = ? AND performer_id = ?", ROW_MAPPER, id, performerId);
        return DataAccessUtils.singleResult(albums); //CollectionUtils.isEmpty(albums) ? null : albums.get(0);
    }

    @Override
    public List<Album> getAll(long performerId) {
        return jdbcTemplate.query("SELECT * FROM albums WHERE performer_id = ? ORDER BY name", ROW_MAPPER, performerId);
    }
}
