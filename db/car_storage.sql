create table car_bodies
(
    id   serial primary key,
    name varchar(200)
);

create table car_engines
(
    id   serial primary key,
    name varchar(200)
);

create table car_transmissions
(
    id   serial primary key,
    name varchar(200)
);

create table cars
(
    id               serial primary key,
    name             varchar(200),
    body_id          int references car_bodies (id),
    engine_id        int references car_engines (id),
    transmissions_id int references car_transmissions (id)
);

insert into car_bodies (name)
values ('Купе'),
       ('Седан'),
       ('Хэтчбек'),
       ('Пикап'),
       ('Кабриолет');

insert into car_engines (name)
values ('Дизельный'),
       ('Бензиновый'),
       ('Электрический'),
       ('Роторный');

insert into car_transmissions (name)
values ('Механическая'),
       ('Гидромеханическая'),
       ('Электромеханическая'),
       ('Гидравлическая'),
       ('Автомат');

insert into cars (name, body_id, engine_id, transmissions_id)
values ('Audi A6', 2, 2, 1),
       ('Nissan Note', null, 2, 5),
       ('Saab 9000', 1, 2, 1),
       ('Porsche Taycan', 1, 3, 5),
       ('Лада 2112', 3, 2, 2),
       ('ГАЗ 2217', null, null, 1),
       ('Kia Carnival', null, 1, 5),
       ('BMW Z4', 1, 2, 5),
       ('BMW Z4', 5, 2, 5);