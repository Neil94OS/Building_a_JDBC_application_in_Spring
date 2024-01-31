package ie.neil.service;

import ie.neil.entities.Salon;
import ie.neil.service.exceptions.SalonIdAlreadyExists;
import ie.neil.service.exceptions.SalonMalformedException;
import ie.neil.service.exceptions.SalonNotFoundException;

import java.util.List;
import java.util.Optional;

public interface SalonService {

    List<Salon> findAll();
    Salon findById(int id) throws SalonNotFoundException;

    Salon findByString(String string) throws  SalonNotFoundException;

    void deleteSalon(int id) throws SalonNotFoundException;

    Salon save(Salon salon) throws SalonMalformedException, SalonIdAlreadyExists;

    boolean editOpeningDays(String newDaysOpen, int id);

    List<Salon> findAllOpen7Days() throws SalonNotFoundException;

}


