package ie.neil.dao;

import ie.neil.entities.Salon;
import ie.neil.entities.SalonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SalonDaoImpl implements  SalonDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Salon> findAll() {
        return jdbcTemplate.query("select * from salons", new SalonRowMapper());
    }

    @Override
    public Optional<Salon> findById(int id) {
        try {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("id", id);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject("select * from salons where salon_Id=:id", mapSqlParameterSource, new SalonRowMapper()));
        } catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Salon> findByString(String string) {
        try {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("string", string);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject("select * from salons where salon_Name=:string", mapSqlParameterSource, new SalonRowMapper()));
        } catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteSalon(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update("delete from salons where salon_Id =:id", mapSqlParameterSource)==1;
    }

    @Override
    public void save(Salon salon)  {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", salon.getSalon_Id());
        mapSqlParameterSource.addValue("name", salon.getSalon_Name());
        mapSqlParameterSource.addValue("address", salon.getSalon_Address());
        mapSqlParameterSource.addValue("number", salon.getSalon_PhoneNumber());
        mapSqlParameterSource.addValue("daysOpen", salon.getSalon_DaysOpen());

        String SQL = "INSERT INTO Salons(salon_Id, salon_Name, salon_Address, salon_PhoneNumber, salon_DaysOpen) values(:id," +
                " :name, :address, :number, :daysOpen)";

        namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource);
    }

    @Override
    public boolean editOpeningDays(String newDaysOpen, int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_days_open", newDaysOpen);
        mapSqlParameterSource.addValue("id", id);
        String SQL ="update salons set salon_DaysOpen = :new_days_open where salon_Id =:id";
        return namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource)==1;
    }

    @Override
    public List<Salon> findAllOpen7Days() {
            return jdbcTemplate.query("select * from salons where salon_DaysOpen = '1111111'", new SalonRowMapper());
    }

}
