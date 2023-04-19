create table departments (
    id serial primary key,
    name varchar(200)
);

create table employees (
    id serial primary key,
    name varchar(200),
    department_id int references departments(id)
);

insert into departments(name) values ('Следственный');
insert into departments(name) values ('Договорно-правовой');
insert into departments(name) values ('Организационно-аналитический');
insert into departments(name) values ('Информационных технологий');

insert into employees(name, department_id) values ('David Bous', 1);
insert into employees(name, department_id) values ('Ivan Sergeev', 1);
insert into employees(name, department_id) values ('Boris Britva', 2);
insert into employees(name, department_id) values ('Anatoly Stepanov', 3);
