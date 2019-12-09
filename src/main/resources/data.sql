---
INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Can view all data but has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMINISTRATOR', 'Administrator - Has permission to perform admin tasks');
INSERT INTO app_role (id, role_name, description) VALUES (3, 'ENGLISH_TEACHER', 'English teacher - Can update students information and grades only in English Academy but has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (4, 'BULGARIAN_TEACHER', 'Bulgarian teacher - Can update students information and grades only in Bulgarian Academy but has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (5, 'RUSSIAN_TEACHER', 'English teacher - Can update students information and grades only in Russian Academy but has no admin rights');



-- USER
-- non-encrypted password: jwtpass
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (1, 'Milen', 'Yakov', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'milen.yakov');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (2, 'Georgi', 'Ivanov', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'georgi.iv');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (3, 'Ken', 'Block', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'ken.uk');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (4, 'Vinislava', 'Lubomirova', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'vini.bg');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (5, 'Katya', 'Ivanova', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'kkatya');



INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);
INSERT INTO user_role(user_id, role_id) VALUES(3,3);
INSERT INTO user_role(user_id, role_id) VALUES(4,4);
INSERT INTO user_role(user_id, role_id) VALUES(5,5);