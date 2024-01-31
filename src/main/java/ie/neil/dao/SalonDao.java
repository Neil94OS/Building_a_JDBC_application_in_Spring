package ie.neil.dao;

import ie.neil.entities.Salon;
import ie.neil.entities.Staff;

import java.util.List;
import java.util.Optional;

public interface SalonDao {

    List<Salon> findAll();
    Optional<Salon> findById(int id);

    Optional<Salon> findByString(String string);

    boolean deleteSalon(int id);

    void save(Salon salon);

    boolean editOpeningDays(String newDaysOpen, int id);

    List<Salon> findAllOpen7Days();

}
