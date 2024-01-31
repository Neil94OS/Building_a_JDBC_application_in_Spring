package ie.neil.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salon {

    private int salon_Id;
    private String salon_Name;
    private String salon_Address;
    private String salon_PhoneNumber;
    private String salon_DaysOpen;

}
