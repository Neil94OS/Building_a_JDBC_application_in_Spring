package ie.neil.entities;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    private int staff_Id;
    private int salon_Id;
    private String staff_Name;
    private String staff_PhoneNumber;
    private int staff_Salary;
}
