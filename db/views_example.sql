create view show_car_info
as
select c.id, c.name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name
from cars c
         left join car_bodies cb on cb.id = c.body_id
         left join car_engines ce on ce.id = c.engine_id
         left join car_transmissions ct on ct.id = c.transmissions_id
where cb.name is not null;
;

select * from show_car_info;

alter view show_car_info rename to show_cars_info_without_null_car_bodies;

select * from show_cars_info_without_null_car_bodies;

drop view show_cars_info_without_null_car_bodies;