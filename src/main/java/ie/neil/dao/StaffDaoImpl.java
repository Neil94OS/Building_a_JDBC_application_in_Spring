package ie.neil.dao;


import ie.neil.dao.dto.StylistsAndSalonRowMapper;
import ie.neil.dao.dto.StylistsAndTheirSalon;
import ie.neil.entities.Salon;
import ie.neil.entities.SalonRowMapper;
import ie.neil.entities.Staff;
import ie.neil.entities.StaffRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StaffDaoImpl implements StaffDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count(){
        return jdbcTemplate.queryForObject("select count(*) from staff", Integer.class);
    }

    @Override
    public List<Staff> findAllInSalon(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.query("select * from staff where salon_Id =:id", mapSqlParameterSource, new StaffRowMapper());

    }

    @Override
    public Optional<Staff> findById(int id) {
        try {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("id", id);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject("select * from staff where staff_Id=:id", mapSqlParameterSource, new StaffRowMapper()));
        } catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteStaff(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update("delete from staff where staff_Id =:id", mapSqlParameterSource)==1;
    }

    @Override
    public void save(Staff staff){
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", staff.getStaff_Id());
        mapSqlParameterSource.addValue("salon_Id", staff.getSalon_Id());
        mapSqlParameterSource.addValue("name", staff.getStaff_Name());
        mapSqlParameterSource.addValue("number", staff.getStaff_PhoneNumber());
        mapSqlParameterSource.addValue("salary", staff.getStaff_Salary());

        String SQL = "INSERT INTO Staff(staff_Id, salon_Id, staff_Name, staff_PhoneNumber, staff_Salary) values(:id, :salon_Id," +
                " :name, :number, :salary)";

        namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource);

    }

    @Override
    public boolean editStaff(int newSalon, int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_salon", newSalon);
        mapSqlParameterSource.addValue("id", id);
        String SQL ="update staff set salon_Id = :new_salon where staff_Id =:id";
        return namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource)==1;
    }

    @Override
    public List<StylistsAndTheirSalon> findStylistAndSalon() {
        String SQL ="select s.salon_Name, p.staff_Name from staff p inner join salons s on s.salon_Id = p.salon_Id";

        return namedParameterJdbcTemplate.getJdbcTemplate().query(SQL, new StylistsAndSalonRowMapper());
    }

    @Override
    public int averageSalaryInSalon(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("select avg(staff_Salary) from staff where salon_Id= :id", mapSqlParameterSource, Integer.class);
    }
}
