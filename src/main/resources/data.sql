INSERT INTO flights (code, departure, arrival) VALUES
('BD674', 'Spain', 'United Kingdom'),
('BA1326', 'Germany', 'Italy'),
('BA1476', 'Lithuania', 'Greece'),
('GF5232', 'Norway', 'Russia'),
('AA8025', 'Czech Republic', 'Sweden'),
('AA7991', 'United Kingdom', 'Poland'),
('AA8017', 'Turkey', 'Germany'),
('BA1442', 'Greece', 'Russia'),
('BA1388', 'Portugal', 'Latvia');

-- USER
-- hashed password: letmein
INSERT INTO security_user (id, username, password, first_name, last_name) VALUES
(1,  'admin', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Administrator', 'Adminstrator'),
(2,  'pilot_jane', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Jane', 'Doe'),
(3,  'pilot_mark', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Mark', 'Smith'),
(4,  'wally', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Walter', 'Adams');

-- ROLES

INSERT INTO security_role (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO security_role (id, role_name, description) VALUES (2, 'ROLE_PILOT', 'Pilot');

INSERT INTO user_role(user_id, role_id) VALUES
(1, 1), -- give admin ROLE_ADMIN
(2, 2),  -- give Jane ROLE_PILOT
(3, 2),  -- give Mark ROLE_PILOT
(4, 1),  -- give Wally ROLE_ADMIN
(4, 2);  -- give Wally ROLE_PILOT
