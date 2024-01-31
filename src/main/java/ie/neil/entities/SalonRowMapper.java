package ie.neil.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonRowMapper implements RowMapper<Salon> {

    @Override
    public Salon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Salon salon = new Salon();
        salon.setSalon_Id(rs.getInt(1));
        salon.setSalon_Name(rs.getString(2));
        salon.setSalon_Address(rs.getString(3));
        salon.setSalon_PhoneNumber(rs.getString(4));

        String days = rs.getString(5);
        String result = daysOpenAsString(days);
        salon.setSalon_DaysOpen(result);
        return salon;
    }
    //Method to map boolean flags to days open

    public String daysOpenAsString(String daysOpen) {
        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            if (daysOpen.charAt(i) == '1') {
                if (!result.isEmpty()) {
                    result.append(", ");
                }
                result.append(weekDays[i]);
            }
        }
        return result.toString();
    }
}
