create table accounts
(
    id       serial primary key,
    login    varchar,
    password varchar
);

create table users
(
    id          serial primary key,
    name        varchar,
    status      boolean,
    accounts_id int references accounts (id) unique
)