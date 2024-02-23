-- Users
INSERT INTO tb_user (name, email, password, cpf, age, role) VALUES ('Hebert', 'hebert@gmail.com', '$2a$10$9wPtp4E2PeIHpmSjI3Xk/u2WhHLLSDsCJJfSdFucm9xQ2do1q18Km', '91002028000', 21, 0); --Senha = hebert1234 ADMIN
INSERT INTO tb_user (name, email, password, cpf, age, role) VALUES ('Solene', 'solene@gmail.com', '$2a$10$ts5Mu4naEvmZJy16q.d9oevxXk/f6JTIvwAV0SyxKL6QXnN03Hpqu', '09767399097', 41, 1); --Senha = isadora123 MEMBER
INSERT INTO tb_user (name, email, password, cpf, age, role) VALUES ('Osny', 'osny@gmail.com', '$2a$10$iZJfArq9JeROAMWF7t8Ww.LuN1PQyyMZyykjXuvwHppxmjxPLD1S6', '15353082060', 41, 2); --Senha = osny1212 USER


--Notification
INSERT INTO tb_notification (text, moment, read, user_Id ) VALUES ('Faz tempo que você não aparece, tudo bem?', TIMESTAMP WITH TIME ZONE '2023-07-13T13:00:00.12345Z', true, 3);
INSERT INTO tb_notification (text, moment, read, user_Id ) VALUES ('Que tal um novo aluguel? Etamos com ofertas incríveis que com certeza irá lhe agradar, venha conerir.', TIMESTAMP WITH TIME ZONE '2023-04-23T17:20:52.12345Z', false, 2);


--Rental
INSERT INTO tb_rental (checkin, checkout, refund_Moment, running, user_Id) VALUES (TIMESTAMP WITH TIME ZONE '2023-07-13T10:00:00.12345Z', TIMESTAMP WITH TIME ZONE '2023-07-30T17:30:00.12345Z', null, false, 3);
INSERT INTO tb_rental (checkin, checkout, refund_Moment, running, user_Id) VALUES (TIMESTAMP WITH TIME ZONE '2023-08-23T10:00:00.12345Z', TIMESTAMP WITH TIME ZONE '2023-08-30T12:00:00.12345Z', null, false, 3);
-- Rent running when all classes are implemented


-- Vehicle / cars
INSERT INTO tb_vehicle (name, brand, plate, manufacture_Year, status, description, price_Per_Hour, price_Per_Day, available, rating) VALUES ('Mercedes-Benz AMG EQS', 'Mercedes', 'A1AAA11', 2023, 0, 'Descubra o futuro da AMG Driving Performance. O Mercedes-AMG EQS 53 4MATIC+ abre as portas para o empolgante futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada. Podem ter ocorrido alterações no produto após a produção dos conteúdos.', 120.0, 500.0, true, 5.0);
INSERT INTO tb_car (id, number_Of_Doors, Trunk_Space, has_Step) VALUES (1, 4, 2.6, false);

INSERT INTO tb_vehicle (name, brand, plate, manufacture_Year, status, description, price_Per_Hour, price_Per_Day, available, rating) VALUES ('Fiat Mobi Like 1.0', 'Fiat', 'B1BBB11', 2021, 1, 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG.', 70.0, 300.0, true, 4.5);
INSERT INTO tb_car (id, number_Of_Doors, Trunk_Space, has_Step) VALUES (2, 4, 1.2, true);

INSERT INTO tb_vehicle (name, brand, plate, manufacture_Year, status, description, price_Per_Hour, price_Per_Day, available, rating) VALUES ('Mercedes-Benz W113', 'Mercedes', 'C1CCC11', 1969, 2, 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada.', 350.0, 1000.0, true, 5.0);
INSERT INTO tb_car (id, number_Of_Doors, Trunk_Space, has_Step) VALUES (3, 2, 2.3, false);

-- Vehicle / motorcycle
INSERT INTO tb_vehicle (name, brand, plate, manufacture_Year, status, description, price_Per_Hour, price_Per_Day, available, rating) VALUES ('BMW K100', 'BMW', 'B1B11', 2022, 0, 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada.', 100.0, 300.0, true, 4.7);
INSERT INTO tb_motorcycle (id, has_Fairing) VALUES (4, true);

INSERT INTO tb_vehicle (name, brand, plate, manufacture_Year, status, description, price_Per_Hour, price_Per_Day, available, rating) VALUES ('Honda CG 160', 'Honda', 'A1A11', 2022, 1, 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada.', 50.0, 200.0, true, 3.4);
INSERT INTO tb_motorcycle (id, has_Fairing) VALUES (5, true);

INSERT INTO tb_vehicle (name, brand, plate, manufacture_Year, status, description, price_Per_Hour, price_Per_Day, available, rating) VALUES ('Ducati 750/900SS', 'Ducati', 'C1C11', 1991, 2, 'Descubra o futuro da AMG Driving Performance. E temos excelentes novidades, porque criamos um veículo totalmente elétrico que também é um autêntico AMG. Como sempre, praticidade no dia a dia e fascinação estão incluídas. Venha para a linha de largada.', 250.0, 600.0, true, 5.0);
INSERT INTO tb_motorcycle (id, has_Fairing) VALUES (6, true);

-- Rental and vehicle
INSERT INTO tb_rentals_done (rental_Id, vehicle_id) VALUES (1, 1);
INSERT INTO tb_rentals_done (rental_Id, vehicle_id) VALUES (2, 6);


-- Deliveries
INSERT INTO tb_deliver (deliver_Moment, rental_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-07-30T18:40:00.12345Z', 1);
INSERT INTO tb_deliver (deliver_Moment, rental_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-08-30T11:58:00.12345Z', 2);


-- Payment
INSERT INTO tb_payment (status, payment_Moment, payment_Amount, rental_Id, payer_Id) VALUES (1, TIMESTAMP WITH TIME ZONE '2023-07-13T10:15:00.12345Z', 200.00, 1, 3);

-- Review
INSERT INTO tb_review (text, quantity_Stars, moment, author_Id, vehicle_Id, rental_Id) VALUES ('Ache o carro ótimo! Ótimo para viagem e onde eu passava chamava atença, do jeito que eu gosto kkkkkkk', 5, TIMESTAMP WITH TIME ZONE '2023-07-30T19:30:00.12345Z', 3, 1, 1);