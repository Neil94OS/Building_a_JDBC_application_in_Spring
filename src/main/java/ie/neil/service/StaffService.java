package ie.neil.service;

import ie.neil.dao.dto.StylistsAndTheirSalon;
import ie.neil.entities.Staff;
import ie.neil.service.exceptions.*;

import java.util.List;

public interface StaffService {

    int count();

    List<Staff> findAllInSalon(int id);

    Staff findById(int id) throws StaffNotFoundException;

    boolean deleteStaff(int id) throws StaffNotFoundException;

    Staff save(Staff staff) throws StaffMalformedException, StaffIdAlreadyExists;

    boolean editStaff(int newSalon, int id);

    List<StylistsAndTheirSalon> findStylistAndSalon();

    int averageSalaryInSalon(int id);


}