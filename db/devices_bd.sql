create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into people(name)
values ('Ivan'),
       ('Masha'),
       ('Anatoliy'),
       ('Maxim');
insert into devices(name, price)
values ('Macbook Pro', 168880),
       ('iPhone 14 Pro', 116190),
       ('Samsung Galaxy A73', 39590),
       ('Samsung Galaxy A03', 4500),
       ('Xiaomi Poco Watch', 3800),
       ('Apple Watch Ultra', 74990);
insert into devices_people(device_id, people_id)
values (2, 1),
       (6, 1),
       (3, 2),
       (5, 2),
       (1, 3),
       (2, 3),
       (4, 4),
       (5, 4);

select avg(price)
from devices;

select p.name, avg(d.price) as Average_Price
from people as p
         join devices_people dp on dp.people_id = p.id
         join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;