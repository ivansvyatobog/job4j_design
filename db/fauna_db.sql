create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Elephant', 17800, '1024-01-10');
insert into fauna(name, avg_age, discovery_date) values('Heron', 2000, '1524-01-01');
insert into fauna(name, avg_age, discovery_date) values('Python', 3800, '1700-01-01');
insert into fauna(name, avg_age, discovery_date) values('Sparrow', 800, '1374-01-01');
insert into fauna(name, avg_age, discovery_date) values('Butterfly', 90, '1964-01-01');
insert into fauna(name, avg_age, discovery_date) values('Bug', 365, null);
insert into fauna(name, avg_age, discovery_date) values('Bacteria', 1, '1900-01-01');
insert into fauna(name, avg_age, discovery_date) values('Jellyfish', 1, '1950-01-01');
insert into fauna(name, avg_age, discovery_date) values('Tiger', 8000, '1324-01-01');
insert into fauna(name, avg_age, discovery_date) values('Scorpio', 600, '1842-01-01');
insert into fauna(name, avg_age, discovery_date) values('Stingray', 900, '1024-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 AND avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';