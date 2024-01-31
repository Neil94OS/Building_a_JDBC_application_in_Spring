package ie.neil.service;

import ie.neil.dao.StaffDao;
import ie.neil.dao.dto.StylistsAndTheirSalon;
import ie.neil.entities.Staff;
import ie.neil.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffDao staffDao;
    @Override
    public int count() {
        return staffDao.count();
    }

    @Override
    public List<Staff> findAllInSalon(int id)  {
        return staffDao.findAllInSalon(id);
    }

    @Override
    public Staff findById(int id) throws StaffNotFoundException {
        return staffDao.findById(id).orElseThrow(()-> new StaffNotFoundException("Staff with id:"+id+" not found."));
    }

    @Override
    public boolean deleteStaff(int id) throws StaffNotFoundException{
        if (!staffDao.deleteStaff(id))
            throw new StaffNotFoundException("Staff with id "+id+" not found.");
        return false;
    }

    @Override
    public Staff save(Staff staff) throws StaffMalformedException, StaffIdAlreadyExists {

        if (staffDao.findById(staff.getStaff_Id()).isPresent()) throw new StaffIdAlreadyExists("Staff with id:"+ staff.getStaff_Id()+" already exists");

        if (staff.getStaff_Name().isBlank())
            throw new StaffMalformedException("Staff cannot be named blank");

        if (staff.getStaff_Salary()<0)
            throw new StaffMalformedException("Staff salary must be positive");

        if (staff.getStaff_Id()<0)
            throw new StaffMalformedException("Staff id invalid");

        staffDao.save(staff);
        return staffDao.findById(staff.getSalon_Id()).get();
    }

    @Override
    public boolean editStaff(int newSalon, int id) {
        return staffDao.editStaff(newSalon, id);
    }

    @Override
    public List<StylistsAndTheirSalon> findStylistAndSalon() {
        return staffDao.findStylistAndSalon();
    }

    @Override
    public int averageSalaryInSalon(int id) {
        return staffDao.averageSalaryInSalon(id);
    }
}
