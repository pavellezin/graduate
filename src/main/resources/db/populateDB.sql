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
('ADMIN', 100001);

INSERT INTO restaurants (name, address, web) VALUES
('Basilio','Entuziastov str','basilio.ru'),
('Vegan','Blinnaya str','vegan.ru');

INSERT INTO dishes (date, description, price, restaurant_id) VALUES
('2020-11-08','Salat',10.00, 100003),
('2020-11-10','Salat',12.00, 100003),
('2020-11-10','Soup',15.00, 100003),
('2020-11-10','Desert',7.00, 100003);

INSERT INTO ratings (date, vote, user_id, restaurant_id) VALUES
('2020-11-09', 5, 100001, 100003),
('2020-11-09', 4, 100002, 100003),
('2020-11-09', 4, 100000, 100003),
('2020-11-10', 4, 100000, 100003),
('2020-11-09', 5, 100001, 100004),
('2020-11-09', 4, 100002, 100004),
('2020-11-10', 5, 100001, 100004);