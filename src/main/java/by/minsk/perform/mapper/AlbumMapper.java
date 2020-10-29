package by.minsk.perform.mapper;

import by.minsk.perform.model.Album;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Alena_Papruha
 * @version 1.1
 * @since 28 Oct, 2020
 */

@Component
public class AlbumMapper implements RowMapper<Album> {

    @Override
    public Album mapRow(ResultSet rs, int rowNum) throws SQLException {

        Object[] objArray = (Object[]) rs.getArray("tracks").getArray();
        String[] tracks = new String[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            tracks[i] = objArray[i].toString();
        }
        return new Album(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("year"),
                Arrays.asList(tracks));
    }
}
