import ie.neil.Config;
import ie.neil.dao.SalonDao;
import ie.neil.dao.SalonDaoImpl;
import ie.neil.dao.StaffDao;
import ie.neil.dao.StaffDaoImpl;
import ie.neil.entities.Salon;
import ie.neil.entities.Staff;
import ie.neil.service.exceptions.SalonIdAlreadyExists;
import ie.neil.service.exceptions.SalonMalformedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@ActiveProfiles("test")
public class DaoTests {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    @Order(1)
    public void testMessagesBean(){
        Assertions.assertNotNull(applicationContext.getBean(MessageSource.class));
    }

    @Test
    @Order(2)
    public void testStaffCount(){
        StaffDao staffDao= applicationContext.getBean(StaffDaoImpl.class);
        Assertions.assertEquals(9, staffDao.count());
    }

    @Test
    @Order(3)
    public void testSalonFindAll(){
        SalonDao salonDao= applicationContext.getBean(SalonDaoImpl.class);
        List<Salon> salon= salonDao.findAll();
        Assertions.assertEquals(6, salon.size());
    }

    @Test
    @Order(4)
    public void testSalonFindByPrimaryKey(){
        SalonDao salonDao= applicationContext.getBean(SalonDaoImpl.class);
        Optional<Salon> salon= salonDao.findById(1);
        Assertions.assertTrue(salon.isPresent());
        Assertions.assertEquals("Neils Salon",salon.get().getSalon_Name());
    }

    @Test
    @Order(5)
    public void testSalonFindByString(){
        SalonDao salonDao= applicationContext.getBean(SalonDaoImpl.class);
        Optional<Salon> salon= salonDao.findByString("Hair Today");
        Assertions.assertTrue(salon.isPresent());
        Assertions.assertEquals("Hair Today",salon.get().getSalon_Name());
    }

    @Test
    @Order(6)
    public void testFindAllStaffInSalon(){
        StaffDao staffDao= applicationContext.getBean(StaffDaoImpl.class);
        List<Staff> staff= staffDao.findAllInSalon(1);
        Assertions.assertEquals(3,staff.size());
    }


    @Test
    @Order(8)
    public void testDeleteSalon(){
        SalonDao salonDao= applicationContext.getBean(SalonDaoImpl.class);
        Assertions.assertTrue(salonDao.deleteSalon(1));

    }

    @Test
    @Order(7)
    public void testDeleteStaff(){
        StaffDao staffDao= applicationContext.getBean(StaffDaoImpl.class);
        Assertions.assertTrue(staffDao.deleteStaff(1));

    }

    @Test
    @Order(9)
    public void testDeleteStaffNotFound(){
        StaffDao staffDao= applicationContext.getBean(StaffDaoImpl.class);
        Assertions.assertFalse(staffDao.deleteStaff(11111));

    }

    @Order(10)
    @Test
    public void testStaffSave(){
        StaffDao staffDao = applicationContext.getBean(StaffDaoImpl.class);
        int count = staffDao.count();
        Staff staff = new Staff(10, 2, "Shauna Hill", "0876654329", 24750);
        staffDao.save(staff);
        Assertions.assertEquals(count+1, staffDao.count());
    }

    @Order(11)
    @Test
    public void testSalonSave() throws SalonMalformedException, SalonIdAlreadyExists {
        SalonDao salonDao = applicationContext.getBean(SalonDaoImpl.class);
        Salon salon = new Salon(7, "New Salon", "Stone Hill", "0876655439", "1111110");
        salonDao.save(salon);
        Assertions.assertEquals(7, salonDao.findAll().size());
    }


}
