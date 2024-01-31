INSERT INTO Salons(salon_Id, salon_Name, salon_Address, salon_PhoneNumber, salon_DaysOpen)
VALUES
    ( 1, 'Neils Salon', 'Ballincollg, Cork', '0871234567', '1111111'),
    ( 2, 'Neils Salon2', 'Mahon, Cork', '0871255643', '0111110'),
    ( 3, 'Neils Salon3', 'Tallaght, Dublin', '0834567765', '0111110'),
    ( 4, 'Bobs Salon', 'Bishopstown, Cork', '0871233345', '1111110'),
    ( 5, 'Hair Today', 'Youghal, Cork', '0871112359', '1111111'),
    ( 6, 'Ray Salon', 'Limerick City, Limerick', '0876789443', '1111111');

INSERT INTO Staff(staff_Id, salon_Id, staff_Name, staff_PhoneNumber, staff_Salary)
VALUES
    (1, 1, 'Mary Keating', '0871234444', 30000),
    (2, 3, 'Rachael Murphy', '0874445679', 25000),
    (3, 4, 'Mary Jones', '0879997536', 22000),
    (4, 4, 'Sheil Neill', '08754267536', 22500),
    (5, 1, 'Leona Bridge', '0875215668', 21000),
    (6, 3, 'Cait Buckley', '0875886521', 19500),
    (7, 2, 'Taylor Taylor', '0872100024', 22600),
    (8, 6, 'Wendy Williams', '0870256885', 26500),
    (9, 5, 'Jess Wind', '0872557800', 28750);



