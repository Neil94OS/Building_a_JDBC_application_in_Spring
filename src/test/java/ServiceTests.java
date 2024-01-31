import ie.neil.Config;
import ie.neil.dao.StaffDao;
import ie.neil.dao.StaffDaoImpl;
import ie.neil.dao.dto.StylistsAndTheirSalon;
import ie.neil.entities.Staff;
import ie.neil.service.exceptions.StaffIdAlreadyExists;
import ie.neil.service.exceptions.StaffMalformedException;
import ie.neil.service.exceptions.StaffNotFoundException;
import ie.neil.service.StaffService;
import ie.neil.service.StaffServiceImpl;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
@ActiveProfiles("test")
public class ServiceTests {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    @Order(1)
    public void testMessagesBean(){
        Assertions.assertNotNull(applicationContext.getBean(MessageSource.class));
    }

    @Test
    @Order(2)
    public void testStaffFindAllInSalon() throws StaffNotFoundException {
        StaffService staffService= applicationContext.getBean(StaffServiceImpl.class);
        Staff staff= staffService.findById(1);
        List<Staff> result= staffService.findAllInSalon(staff.getStaff_Id());
        Assertions.assertEquals(3, result.size());
    }

    @Test
    @Order(3)
    public void testDeleteStaff() throws StaffNotFoundException{
        StaffService staffService= applicationContext.getBean(StaffServiceImpl.class);
        Assertions.assertTrue(staffService.deleteStaff(1));
    }

    @Test
    @Order(4)
    public void testSaveStaff() throws StaffIdAlreadyExists, StaffMalformedException {
        StaffService staffService= applicationContext.getBean(StaffServiceImpl.class);
        int count = staffService.count();
        Staff staff = new Staff(10, 2, "Shauna Hill", "0876654329", 24750);
        staffService.save(staff);
        Assertions.assertEquals(count+1, staffService.count());
    }

    @Test
    @Order(5)
    public void testEditStaff()  {
        StaffService staffService= applicationContext.getBean(StaffServiceImpl.class);
        staffService.editStaff(2,8);
        Assertions.assertEquals(2, staffService.findAllInSalon(2).size());

    }

    @Test
    @Order(6)
    public void testFindStaffAndSalon() throws StaffIdAlreadyExists, StaffMalformedException {
        StaffService staffService= applicationContext.getBean(StaffServiceImpl.class);
        List<StylistsAndTheirSalon> result = staffService.findStylistAndSalon();
        Assertions.assertEquals(result, staffService.findStylistAndSalon());
    }

    @Test
    @Order(7)
    public void testAvgSalary(){
        StaffService staffService = applicationContext.getBean(StaffServiceImpl.class);
        int result = staffService.averageSalaryInSalon(1);
        Assertions.assertEquals(23500, result);
    }



}
