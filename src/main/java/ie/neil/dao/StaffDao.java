package ie.neil.dao;

import ie.neil.dao.dto.StylistsAndTheirSalon;
import ie.neil.entities.Salon;
import ie.neil.entities.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffDao {
    int count();

    List<Staff> findAllInSalon(int id);

    Optional<Staff> findById(int id);

    boolean deleteStaff(int id);

    void save(Staff staff);

    boolean editStaff(int newSalon, int id);

    List<StylistsAndTheirSalon> findStylistAndSalon();

    int averageSalaryInSalon(int id);

}
