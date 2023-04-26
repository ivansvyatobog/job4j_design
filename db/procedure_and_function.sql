create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    language 'plpgsql'
as $$
BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
END
$$;

call insert_data('product_5', 'produser_3', 1234, 11);

create or replace procedure update_data(u_count integer, tax float, u_id integer)
    language 'plpgsql'
as $$
BEGIN
    if u_count > 0 THEN
        update products set count = count - u_count where id = u_id;
    end if;
    if tax > 0 THEN
        update products set price = price + price * tax;
    end if;
END;
$$;

call update_data(10, 0, 1);

create or replace procedure delete_data_count_less_than_3()
language 'plpgsql'
as $$
BEGIN
    delete from products where count < 3;
END
$$;

call delete_data_count_less_than_3();

create or replace function delete_data_when_less_then_count(prod_count int)
    returns integer
    language 'plpgsql'
as
$$
declare
    result integer;
begin
    delete from products
    where count < prod_count;
    select into result count from products;
    return result;
end;
$$;

select delete_data_when_less_then_count(13);

