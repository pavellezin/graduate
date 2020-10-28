DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('user1', 'user1@paullezin.pro', 'user1'),
('user2', 'user2@paullezin.pro', 'user2'),
('admin', 'admin@paullezin.pro', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
('USER', 100000),
('ADMIN', 100001);

INSERT INTO restaurants (name, address, web, rating) VALUES
('Basilio','Entuziastov str','basilio.ru',5),
('Vegan','Blinnaya str','vegan.ru',4);