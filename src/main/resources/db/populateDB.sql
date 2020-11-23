DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM dishes;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('user1', 'user1@paullezin.pro', 'user1'),
('user2', 'user2@paullezin.pro', 'user2'),
('admin', 'admin@paullezin.pro', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
('USER', 100000),
('ADMIN', 100001),
('ADMIN', 100002),
('USER', 100002);

INSERT INTO restaurants (name, address, web) VALUES
('Basilio','Entuziastov str','basilio.ru'),
('Vegan','Blinnaya str','vegan.ru');

INSERT INTO dishes (description, price, restaurant_id) VALUES
('Salat',10.00, 100004),
('Salat',12.00, 100003),
('Soup',15.00, 100003),
('Desert',7.00, 100003);

INSERT INTO ratings (date, vote, user_id, restaurant_id) VALUES
('2020-11-10',3, 100000, 100003),
('2020-11-10',5, 100000, 100004),
('2020-11-11',5, 100000, 100004);

INSERT INTO ratings (vote, user_id, restaurant_id) VALUES
(5, 100000, 100004),
(5, 100001, 100003),
(3, 100001, 100004),
(5, 100002, 100003),
(4, 100002, 100004);
