create table teens
(
    id     serial primary key,
    name   varchar(200),
    gender char
);

insert into teens(name, gender)
values ('Zak', 'M'),
       ('Lily', 'F'),
       ('Masha', 'F'),
       ('Vitaliy', 'M'),
       ('David', 'M'),
       ('Ivan', 'M'),
       ('Sasha', 'F'),
       ('Natasha', 'F');

select boy.name as boy_name, girl.name as girl_name
from teens boy
         cross join teens girl where boy.gender != girl.gender;