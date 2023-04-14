select *
from product
         join type on type.id = product.type_id
where type.name = 'СЫР';

select *
from product
where lower(name) like '%мороженое%';

select *
from product
where expired_data < current_date;

select *
from product
where price = (select max(price) from product);

select type.name, count(product)
from type
         join product on type.id = product.type_id
group by type.name;

select type.name, count(product)
from type
         join product on type.id = product.type_id
group by type.name;

select *
from product
         join type on type.id = product.type_id
where type.name = 'СЫР'
   or type.name = 'МОЛОКО';

select type.name, COUNT(type_id)
from product
         inner join type ON type.id = product.type_id
group by type.name;