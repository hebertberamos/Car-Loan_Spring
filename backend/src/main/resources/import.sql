-- Users
INSERT INTO tb_user (first_Name, last_Name, email, password, cpf, age) VALUES ('Hebert', 'Benigno', 'hebertramos09@gmail.com', 'HeMa04#*', '08693666371', 21);
INSERT INTO tb_user (first_Name, last_Name, email, password, cpf, age) VALUES ('Mayane', 'Aguiar', 'mayanelaguiar@gmail.com', 'seila1404', '08692761371', 20);

--Cars
INSERT INTO tb_car (vehicle_Name, brand, plate, year_Manufacture, status_Vehicle, number_Of_Doors, description, price_Per_Hour, price_Per_Day) VALUES ('Mercedes-Benz AMG EQS', 'Mercedes', 'A1AAA11', 2023, 'VIP', 4, 'Descubra o futuro da AMG Driving Performance. O Mercedes-AMG EQS 53 4MATIC+ abre as portas para o empolgante futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada. Podem ter ocorrido alterações no produto após a produção dos conteúdos.', 120.0, 500.0);
INSERT INTO tb_car (vehicle_Name, brand, plate, year_Manufacture, status_Vehicle, number_Of_Doors, description, price_Per_Hour, price_Per_Day) VALUES ('Fiat Mobi Like 1.0', 'Fiat', 'B1BBB11', 2021, 'POPULAR', 4, 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG.', 70.0, 300.0);
INSERT INTO tb_car (vehicle_Name, brand, plate, year_Manufacture, status_Vehicle, number_Of_Doors, description, price_Per_Hour, price_Per_Day) VALUES ('Mercedes-Benz W113', 'Mercedes', 'C1CCC11', 1969, 'ANTIQUITY', 2, 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada.', 350.0, 1000.0);

--Motorcycles
INSERT INTO tb_motorcycle (vehicle_Name, brand, plate, year_Manufacture, status_Vehicle, description, price_Per_Hour, price_Per_Day, has_Fairing) VALUES ('Honda CG 160', 'Honda', 'A1A11', 2022, 'POPULAR', 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada.', 50.0, 200.0, 1);
INSERT INTO tb_motorcycle (vehicle_Name, brand, plate, year_Manufacture, status_Vehicle, description, price_Per_Hour, price_Per_Day, has_Fairing) VALUES ('BMW K100', 'BMW', 'B1B11', 2022, 'VIP', 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada.', 100.0, 300.0, 0);
INSERT INTO tb_motorcycle (vehicle_Name, brand, plate, year_Manufacture, status_Vehicle, description, price_Per_Hour, price_Per_Day, has_Fairing) VALUES ('Ducati 750/900SS', 'Ducati', 'C1C11', 1991, 'ANTIQUITY', 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada.', 250.0, 600.0, 1);

--Rental
INSERT INTO tb_rental (checkin, checkout) VALUES (TIMESTAMP WITH TIME ZONE '2023-07-13T10:00:00.12345Z', TIMESTAMP WITH TIME ZONE '2023-07-30T17:30:00.12345Z')


--INSERT INTO tb_rental_user (rental_Id, user_Id) VALUES (1, 1);

--INSERT INTO tb_rental_car (rental_Id, car_Id) VALUES (1, 1);