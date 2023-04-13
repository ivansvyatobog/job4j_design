create table car_owner(
    id serial primary key,
    name varchar
);

create table car_model(
    id serial primary key,
    name varchar,
    owner_id int references car_owner(id)
);

insert into car_owner(id, name) values (1, 'Ivan');
insert into car_owner(id, name) values (2, 'Boris');
insert into car_owner(id, name) values (3, 'Sergey');
insert into car_owner(id, name) values (4, 'Alex');

insert into car_model(name, owner_id) values ('Ford Mustang', 1);
insert into car_model(name, owner_id) values ('Lada Priora', 1);
insert into car_model(name, owner_id) values ('Subaru Impreza', 2);
insert into car_model(name, owner_id) values ('Skoda Superb', 3);
insert into car_model(name, owner_id) values ('Porsche 911', 4);
insert into car_model(name, owner_id) values ('Porsche Cayenne', 4);

select co.name, cm.name
from car_owner as co join car_model as cm on co.id = cm.owner_id;

select co.name as Имя, cm.name as Модель
from car_owner as co join car_model as cm on co.id = cm.owner_id;

select co.name as "Имя Владельца", cm.name as "Модель Машины"
from car_owner as co join car_model as cm on co.id = cm.owner_id;