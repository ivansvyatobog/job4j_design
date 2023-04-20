select c.id, c.name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name
from cars c
         left join car_bodies cb on cb.id = c.body_id
         left join car_engines ce on ce.id = c.engine_id
         left join car_transmissions ct on ct.id = c.transmissions_id;

select cb.id, cb.name as body_name
from car_bodies cb
         left join cars c on cb.id = c.body_id
where c.body_id is null;

select ce.id, ce.name as engine_name
from car_engines ce
         left join cars c on ce.id = c.engine_id
where c.engine_id is null;

select ct.id, ct.name as transmission_name
from car_transmissions ct
         left join cars c on ct.id = c.transmissions_id
where c.transmissions_id is null;