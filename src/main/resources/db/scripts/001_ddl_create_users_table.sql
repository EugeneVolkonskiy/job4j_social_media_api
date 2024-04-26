create table users
(
    id serial primary key,
    login text unique not null,
    email text not null,
    password text not null
);