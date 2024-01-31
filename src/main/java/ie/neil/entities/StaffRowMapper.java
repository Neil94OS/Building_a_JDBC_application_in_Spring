package ie.neil.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {

    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staff = new Staff();
        staff.setStaff_Id(rs.getInt(1));
        staff.setSalon_Id(rs.getInt(2));
        staff.setStaff_Name(rs.getString(3));
        staff.setStaff_PhoneNumber(rs.getString(4));
        staff.setStaff_Salary(rs.getInt(5));
        return staff;
    }
}
