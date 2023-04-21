create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    int
);

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function tax()
    returns trigger as
$$
BEGIN
    update products
    set price = price * 1.18
    where id = (select id from inserted);
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create or replace trigger tax_trigger
    after insert
    on products
    referencing new table as inserted
    for each statement
execute procedure tax();

create or replace function tax_row()
    returns trigger as
$$
BEGIN
    update products
    set price = price * 1.18;
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create or replace trigger tax_for_row
    before insert
    on products
    for each row
execute procedure tax_row();


insert into products(name, producer, count, price)
values ('Пиво', 'Очаково', 3, 20);

create or replace function add_info()
    returns trigger as
$$
BEGIN
    insert into history_of_price (name, price, date)
    values (NEW.name, NEW.price, now());
    RETURN NEW;
END;
$$
    LANGUAGE 'plpgsql';

create trigger add_into_table_trigger
    after insert
    on products
    for each row
execute procedure add_info();

select * from history_of_price