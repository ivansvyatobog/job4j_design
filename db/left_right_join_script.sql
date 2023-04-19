select * from departments d left join employees e on d.id = e.department_id where e.id is null;

select * from departments d left join employees e on d.id = e.department_id;

select d.id, d.name, e.id, e.name, e.department_id  from employees e right join departments d on e.department_id = d.id;