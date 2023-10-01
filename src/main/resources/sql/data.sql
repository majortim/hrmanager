TRUNCATE TABLE users;
TRUNCATE TABLE authorities;

INSERT INTO users (username, password)
VALUES ('admin', '{bcrypt}$2a$10$poiwIoope2U4/l35F1iSEO4YJgUlBClgxnrbGRj56aRIySP23GuZe');

INSERT INTO authorities (auth_id, username, authority)
VALUES (1, 'admin', 'ROLE_ADMIN');
