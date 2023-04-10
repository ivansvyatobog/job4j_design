create table car_brand
(
    id   serial primary key,
    name varchar(255)
);

create table car_models
(
    id   serial primary key,
    name varchar(255),
    car_brand_id int references car_brand(id)
);