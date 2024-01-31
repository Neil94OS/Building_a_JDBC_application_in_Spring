package ie.neil.service;

import ie.neil.dao.SalonDao;
import ie.neil.entities.Salon;
import ie.neil.service.exceptions.SalonIdAlreadyExists;
import ie.neil.service.exceptions.SalonMalformedException;
import ie.neil.service.exceptions.SalonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalonServiceImpl implements SalonService{

    @Autowired
    private SalonDao salonDao;

    @Override
    public List<Salon> findAll() {
        return salonDao.findAll();
    }

    @Override
    public Salon findById(int id) throws SalonNotFoundException {
        return salonDao.findById(id).orElseThrow(()-> new SalonNotFoundException("Salon with id:"+id+" not found."));
    }

    @Override
    public Salon findByString(String string) throws SalonNotFoundException {
        return salonDao.findByString(string).orElseThrow(()-> new SalonNotFoundException("Salon with name:"+string+" not found."));
    }

    @Override
    public void deleteSalon(int id) throws SalonNotFoundException {
        if (! salonDao.deleteSalon(id))
            throw new SalonNotFoundException("Salon with id "+id+" not found.");
    }

    @Override
    public Salon save(Salon salon) throws SalonMalformedException, SalonIdAlreadyExists{

        if (salonDao.findById(salon.getSalon_Id()).isPresent()) throw new SalonIdAlreadyExists("Studio with id:"+ salon.getSalon_Id()+" already exists");

        if (salon.getSalon_Name().isBlank())
            throw new SalonMalformedException("Salon cannot be named blank");

        if (salon.getSalon_PhoneNumber().isBlank())
            throw new SalonMalformedException("Salon phone number cannot be blank");

        if (salon.getSalon_Address().isBlank())
            throw new SalonMalformedException("Salon address cannot be blank");

        if (salon.getSalon_Id()<0)
            throw new SalonMalformedException("Salon id invalid");

        salonDao.save(salon);
        return salonDao.findById(salon.getSalon_Id()).get();
    }

    @Override
    public boolean editOpeningDays(String newDaysOpen, int id) {
        return salonDao.editOpeningDays(newDaysOpen, id);
    }

    @Override
    public List<Salon> findAllOpen7Days() throws SalonNotFoundException {
        return salonDao.findAllOpen7Days();
    }
}
