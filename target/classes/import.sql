
insert into category(id, name, description) values(1,'Alimentare','Produse alimentare');
insert into category(id, name, description) values(2,'Racoritoare','Bauturi racoritoare');
insert into category(id, name, description) values(3,'Nealimentare','Produse nealimentare');
insert into category(id, name, description) values(4,'Cosmetice','Produse cosmetice');
insert into category(id, name, description) values(5,'Electronice','Produse electronice');


insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(1,'Popescu','Viorel','Craiova','Mihai Viteazu','19B','0782350230','popescu.viorel@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(2,'Ciobanu','Tudor','Cluj','Roma','23','0785949329','ciobanu.tudor23@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(3,'Popovici','Valentin','Brasov','Cetatii','25C','0725975019','valentin.pop@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(4,'Stan','Marcela','Constanta','Pescarusului','50','0730189315','marcela_stan@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(5,'Iacob','Simona','Galati','Mugurasului','120','0748416011','simo_iacob33@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(6,'Ionescu','Maria','Bucuresti','Rezervelor','56','0775838957','mariaionescu21@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(7,'Constantinescu','Ion','Bucuresti','Aleea Rozelor','17','0788423374','ion.ionel96@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(8,'Militaru','Veronica','Slatina','Cireasov','15','0776271569','militaru_vero@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(9,'Georgescu','Mihaela','Slobozia','Unirii','56','0744265925','miki_georgescu89@yahoo.com');
insert into customer( Id, First_Name, Last_Name ,City, Street, Street_Number, Phone, Email) values(10,'Voinea','Razvan','Craiova','Crizantemelor','35g','0796202757','razvan_voinea17@yahoo.com');


insert into department(id,city,name) VALUES(1,'Bucuresti','Vanzari');
insert into department(id,city,name) VALUES(2,'Bucuresti','Marketing');
insert into department(id,city,name) VALUES(3,'Cluj','Financiar');
insert into department(id,city,name) VALUES(4,'Timisoara','Cercetare si Dezvoltare');
insert into department(id,city,name) VALUES(5,'Bucuresti','Logistica/Aprovizionare');



insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(1,'1971-05-06','maria_iliescu@yahoo.com','Iliescu','Maria','0798298918',2300,4);
insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(2,'1973-06-23','popescu_mirel2@yahoo.com','Popescu','Mirel','0782500998',1950,4);
insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(3,'1969-05-02','mirecea_grig@yahoo.com','Dima','Larisa','0762809622',3000,2);
insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(4,'1983-08-21','angajat1@yahoo.com','Grigore','Ioana','0711589749',2135,2);
insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(5,'1980-01-14','badea.remus@yahoo.com','Badea','Remus','0749625906',3500,1);
insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(6,'1970-11-17','ananedelcu@yahoo.com','Nedelcu','Ana','0778198901',2340,3);
insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(7,'1975-10-28','stamate.nico@yahoo.com','Stamate','Nicoleta','0773327336',2950,5);
insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(8,'2000-11-05','mmatei@yahoo.com','Marcu','Matei','0792051880',4567,5);
insert into employee(id,date,email,first_name,last_name,phone,salary,dep_id) values(9,'1981-07-13','angy_matei29@yahoo.com','Matei','Angela','0730083526',3692,5);

insert into provider(id,city,name) values(1,'Bucuresti','Astron SRL' );
insert into provider(id,city,name) values(2,'Bucuresti','S.D Trading' );
insert into provider(id,city,name) values(3,'Bucuresti','Flamingo Impex' );
insert into provider(id,city,name) values(4,'Bucuresti','H-Energy');
insert into provider(id,city,name) values(5,'Cluj','Coca-Cola');

insert into provider_info(id,address,email,iban,provider_id) values(1,'asdasda','s@yahoo.com','RO1231231231',1);
insert into provider_info(id,address,email,iban,provider_id) values(2,'Bucuresti sector 5','astron_srl@yahoo.com','RO53RZBR6631933711147466' ,2);
insert into provider_info(id,address,email,iban,provider_id) values(3,'Bucuresti sector 3','trading.sd@yahoo.com','RO07PORL8679229425536357',3 );
insert into provider_info(id,address,email,iban,provider_id) values(4,'Bucuresti sector 2','imp_flam54@yahoo.com','RO17PORL1153937349485191',4 );
insert into provider_info(id,address,email,iban,provider_id) values(5,'Cluj Rozelor 56','energyH@yahoo.com','RO76PORL8414887654415244',5 );


insert into product(id,description,name,price,category_id,provider_id) values (1,'Iaurt natural','Iaurt',2,1,3);
insert into product(id,description,name,price,category_id,provider_id) values (2,'Biscuiti cu ciocolata','Biscuiti',3.99,3,1);
insert into product(id,description,name,price,category_id,provider_id) values (3,'Doza coca-cola','Cola',3,2,5);
insert into product(id,description,name,price,category_id,provider_id) values (4,'Fanta 2L','Fanta',6.25,2,5);
insert into product(id,description,name,price,category_id,provider_id) values (5,'Bureti de vase','Bureti',2.4,3,1);
insert into product(id,description,name,price,category_id,provider_id) values (6,'Deodorant dove','Deodorant',12.75,4,3);
insert into product(id,description,name,price,category_id,provider_id) values (7,'Sampon cu lavanda','Sampon',9.67,4,3);
insert into product(id,description,name,price,category_id,provider_id) values (8,'Aragaz electric','Aragaz',520,5,4);

INSERT INTO users (username, password, enabled)values ('user','$2a$12$WUWuJOWq4PHXmpyIbtQHoeyVOFaulYHbeDa8Ypw3zXl4FD1eIXUD6',1);
INSERT INTO authorities (username, authority)values ('user', 'ROLE_USER');

INSERT INTO users (username, password, enabled)values ('admin','$2a$12$Q5DjKrt1FVz.CzIa30yghOoET/cmOlMJWSaXBaDzIEEWFV5dDW336',1);
INSERT INTO authorities (username, authority)values ('admin', 'ROLE_ADMIN');