create table car_models
(
    id           serial primary key,
    name         varchar(255),
    model        text,
    manufactured date
);

insert into car_models(name, model, manufactured)
values ('Ваз', 'Priora', '23.08.2016');

select *
from car_models;

update car_models
set name = 'Vaz';

delete
from car_models;

select *
from car_models;