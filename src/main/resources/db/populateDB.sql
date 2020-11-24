DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM dishes;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('user1', 'user1@paullezin.pro', 'user1'),                          -- 100000
('user2', 'user2@paullezin.pro', 'user2'),                          -- 100001
('admin', 'admin@paullezin.pro', 'admin');                          -- 100002

INSERT INTO user_roles (role, user_id) VALUES
('ADMIN', 100000),
('USER', 100000),
('USER', 100001),
('ADMIN', 100002),
('USER', 100002);

INSERT INTO restaurants (name, address, web) VALUES
('Basilio','Entuziastov str','basilio.ru'),                         -- 100003
('Vegan','Blinnaya str','vegan.ru');                                -- 100004

INSERT INTO dishes (description, price, restaurant_id) VALUES
('Salat',10.00, 100004),                                           -- 100005
('Salat',12.00, 100003),                                           -- 100006
('Soup',15.00, 100003),                                            -- 100007
('Desert',7.00, 100003);                                           -- 100008

INSERT INTO ratings (date, vote, user_id, restaurant_id) VALUES
('2020-11-10',3, 100000, 100003),                                 -- 100009
('2020-11-10',5, 100000, 100004),                                 -- 100010
('2020-11-11',5, 100000, 100004);                                 -- 100011

INSERT INTO ratings (vote, user_id, restaurant_id) VALUES
(5, 100000, 100004),                                              -- 100012
(5, 100001, 100003),                                              -- 100013
(4, 100001, 100004),                                              -- 100014
(5, 100002, 100003),                                              -- 100015
(3, 100002, 100004);                                              -- 100016

INSERT INTO restaurants (name, address, web) VALUES
('New Basilio','Entuziastov str 2','new.basilio.ru');               -- 100017