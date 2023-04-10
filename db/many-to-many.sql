create table human
(
    id   serial primary key,
    name varchar
);

create table apartment
(
    id      serial primary key,
    address varchar
);

create table human_apartment
(
    id           serial primary key,
    human_id     int references human (id),
    apartment_id int references apartment (id)
);