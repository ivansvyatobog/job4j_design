create table role
(
    id        serial primary key,
    name varchar
);

create table rules
(
    id   serial primary key,
    name varchar
);

create table state
(
    id   serial primary key,
    name varchar
);

create table users
(
    id      serial primary key,
    name    varchar,
    role_id int references role (id)
);

create table category
(
    id   serial primary key,
    name varchar
);

create table item
(
    id          serial primary key,
    name        varchar,
    user_id     int references users (id),
    state_id    int references state (id),
    category_id int references category (id)
);

create table comments
(
    id      serial primary key,
    comment text,
    item_id int references item (id)
);

create table attachments
(
    id      serial primary key,
    attach  text,
    item_id int references item (id)
);

create table rule_to_roles
(
    id       serial primary key,
    role_id  int references role (id),
    rules_id int references rules (id)
);