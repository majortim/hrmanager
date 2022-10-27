truncate table users;
truncate table authorities;

insert into users (username, password)
values ('admin', '{bcrypt}$2a$10$poiwIoope2U4/l35F1iSEO4YJgUlBClgxnrbGRj56aRIySP23GuZe');

insert into authorities (auth_id, username, authority)
values
    (1, 'admin', 'ROLE_ADMIN');