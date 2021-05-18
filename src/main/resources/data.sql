INSERT INTO flights (code, departure, arrival, dep_date, dep_time, arr_date, arr_time, assigned_to) VALUES
('BD674', 'Spain', 'United Kingdom', '2021-01-01', '12:00:00', '2021-01-02', '12:00:00', null),
('BA1326', 'Germany', 'Italy', '2021-01-01', '12:00:00', '2021-01-01', '12:00:00', null),
('BA1476', 'Lithuania', 'Greece', '2021-01-01', '12:00:00', '2021-01-01', '12:00:00', null),
('GF5232', 'Norway', 'Russia', '2021-01-01', '12:00:00', '2021-01-01', '12:00:00', null),
('AA8025', 'Czech Republic', 'Sweden', '2021-01-01', '12:00:00', '2021-01-01', '12:00:00', null),
('AA7991', 'United Kingdom', 'Poland', '2021-01-01', '12:00:00', '2021-01-01', '12:00:00', null),
('AA8017', 'Turkey', 'Germany', '2021-01-01', '12:00:00', '2021-01-01', '12:00:00', null),
('BA1442', 'Greece', 'Russia', '2021-01-01', '12:00:00', '2021-01-01', '12:00:00', 'pilot_jane'),
('BA1388', 'Portugal', 'Latvia', '2021-01-01', '12:00:00', '2021-01-01', '12:00:00', null);

-- USER
-- hashed password: letmein
INSERT INTO security_user (id, username, password, first_name, last_name, country) VALUES
(1,  'admin', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Administrator', 'Adminstrator', null ),
(2,  'pilot_jane', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Jane', 'Doe', 'Spain'),
(3,  'pilot_mark', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Mark', 'Smith', 'Germany'),
(4,  'wally', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Walter', 'Adams', 'Lithuania');

-- ROLES

INSERT INTO security_role (id, role_name, description) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO security_role (id, role_name, description) VALUES (2, 'ROLE_PILOT', 'Pilot');

INSERT INTO user_role(user_id, role_id) VALUES
(1, 1), -- give admin ROLE_ADMIN
(2, 2),  -- give Jane ROLE_PILOT
(3, 2),  -- give Mark ROLE_PILOT
(4, 1),  -- give Wally ROLE_ADMIN
(4, 2);  -- give Wally ROLE_PILOT
