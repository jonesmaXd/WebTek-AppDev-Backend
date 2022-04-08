-- Here we populate our database with the default users. In production you would not do this, of course
-- This file is executed automatically by Spring Boot, after executing the schema.sql

insert into users (name, username, password)
values ('dave', 'dave123', '$2a$10$nwbEjYKgcomq2rjUPge2JegqI.y4zEcNqRMPdqwFnd1ytorNCQM/y'),
       ('chuck', 'ChuckIsBeast', '$2a$12$/NoknpFFPDlzL3kBryJfsur0yeYC2JFqAs7Fd79ypMP6PN/mtSYmC');

insert into roles (username, role)
values ('ChuckIsBeast', 'ROLE_USER'), ('ChuckIsBeast', 'ROLE_ADMIN'), ('dave123', 'ROLE_USER');