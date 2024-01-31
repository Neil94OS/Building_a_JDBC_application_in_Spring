package ie.neil.dao.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StylistsAndSalonRowMapper implements RowMapper<StylistsAndTheirSalon> {
    @Override
    public StylistsAndTheirSalon mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new StylistsAndTheirSalon(rs.getString(1), rs.getString(2));
    }
}
