CREATE TABLE salons(
                    salon_Id INT PRIMARY KEY,
                    salon_Name VARCHAR(255) NOT NULL,
                    salon_Address VARCHAR(255) NOT NULL,
                    salon_PhoneNumber VARCHAR(13) NOT NULL,
                    salon_DaysOpen VARCHAR(255) NOT NULL
);

CREATE TABLE staff(
                     staff_Id INT PRIMARY KEY,
                     salon_Id INT,
                     staff_Name VARCHAR(255) NOT NULL,
                     staff_PhoneNumber VARCHAR(13),
                     staff_Salary INT CHECK ( staff_Salary>0 ) NOT NULL,
                  foreign key(salon_Id) references salons(salon_Id) on delete cascade
);
