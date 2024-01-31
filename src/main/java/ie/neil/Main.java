package ie.neil;

import ie.neil.dao.SalonDao;
import ie.neil.dao.SalonDaoImpl;
import ie.neil.entities.Salon;
import ie.neil.entities.Staff;
import ie.neil.service.SalonService;
import ie.neil.service.SalonServiceImpl;
import ie.neil.service.StaffService;
import ie.neil.service.StaffServiceImpl;
import ie.neil.service.exceptions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Main {
    public static void main(String[] args) throws StaffNotFoundException, SalonNotFoundException, StaffIdAlreadyExists, StaffMalformedException, SalonMalformedException, SalonIdAlreadyExists {
        System.setProperty("spring.profiles.active", "tests");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        //Output welcome in English and French
        System.out.println(applicationContext.getMessage("intro", null, Locale.getDefault()));
        System.out.println(applicationContext.getMessage("intro", null, Locale.FRENCH));
        System.out.println("\n");


        //New Dao and service to demonstrate functionality
        SalonDao salonDao= applicationContext.getBean(SalonDaoImpl.class);
        SalonService salonService= applicationContext.getBean(SalonServiceImpl.class);
        StaffService staffService= applicationContext.getBean(StaffServiceImpl.class);

        //All staff in salon with Id 1
        System.out.println(applicationContext.getMessage("allInSalonWithId", null, Locale.getDefault())+staffService.findAllInSalon(1));
        System.out.println("\n");
        //Add a new staff member
        Staff newStaff = new Staff(10,1,"Newest Member", "08725678754",19800);
        staffService.save(newStaff);
        System.out.println(applicationContext.getMessage("addedStylist", null, Locale.getDefault()));
        //Show salon with new staff added
        System.out.println(applicationContext.getMessage("allInSalonWithId", null, Locale.getDefault())+staffService.findAllInSalon(1));
        System.out.println("\n");
        //Move a stylist to a new salon
        System.out.println(applicationContext.getMessage("movedStylist", null, Locale.getDefault())+staffService.editStaff(1,6));
        //Show all staff again
        System.out.println(applicationContext.getMessage("allInSalonWithId", null, Locale.getDefault())+staffService.findAllInSalon(1));
        System.out.println("\n");
        //Delete a stylist
        System.out.println(applicationContext.getMessage("deletedStylist", null, Locale.getDefault())+staffService.deleteStaff(6));
        //Show staff again
        System.out.println(applicationContext.getMessage("allInSalonWithId", null, Locale.getDefault())+staffService.findAllInSalon(1));
        System.out.println("\n");
        //Show Average salary in salon 1
        System.out.println(applicationContext.getMessage("averageSalary", null, Locale.getDefault())+ staffService.averageSalaryInSalon(1));
        System.out.println("\n");
        //Display record of all stylists and their salons
        System.out.println(applicationContext.getMessage("stylistsAndSalon", null, Locale.getDefault())+ staffService.findStylistAndSalon());
        System.out.println("\n");
        //Show all salons and details
        System.out.println(applicationContext.getMessage("allSalon", null, Locale.getDefault())+salonService.findAll());
        System.out.println("\n");
        //New salon added
        Salon newSalon = new Salon(7,"New Salon", "New Address", "0812345678", "1111111");
        salonService.save(newSalon);
        System.out.println(applicationContext.getMessage("salonAdded", null, Locale.getDefault()));
        System.out.println("\n");
        //Show all salons again
        System.out.println(applicationContext.getMessage("allSalon", null, Locale.getDefault())+salonService.findAll());
        System.out.println("\n");
        //Find salon by string Hair Today
        System.out.println(applicationContext.getMessage("salonByString", null, Locale.getDefault())+salonService.findByString("Hair Today"));
        System.out.println("\n");
        //Update opening days
        System.out.println(applicationContext.getMessage("updatedOpeningDays", null, Locale.getDefault())+salonService.editOpeningDays("1100000",1));
        System.out.println("\n");
        //Find by primary key
        System.out.println(applicationContext.getMessage("salonByPrimaryKey", null, Locale.getDefault())+salonService.findById(1));
        System.out.println("\n");
        //Delete salon
        System.out.println(applicationContext.getMessage("salonDeleted", null, Locale.getDefault())+salonDao.deleteSalon(2));
        System.out.println("\n");
        //Show all salons again
        System.out.println(applicationContext.getMessage("allSalon", null, Locale.getDefault())+salonService.findAll());
        //Show all staff again, should be deleted from deleting salon
        System.out.println(applicationContext.getMessage("allInSalonWithId", null, Locale.getDefault())+staffService.findAllInSalon(2));
        System.out.println("\n");
        //Show all salons open 7 days a week
        System.out.println(applicationContext.getMessage("allSalonsOpen7Days", null, Locale.getDefault())+salonService.findAllOpen7Days());
        System.out.println(applicationContext.getMessage("allSalonsOpen7Days", null, Locale.FRENCH)+salonService.findAllOpen7Days());

    }
}