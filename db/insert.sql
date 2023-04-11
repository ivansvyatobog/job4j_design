insert into role(name) values ('Админ');

insert into rules(name) values ('Управление');

insert into state(name) values ('В обработке');

insert into users(name, role_id) values ('Сергей Иванов', 1);

insert into category(name) values ('Срочные');

insert into item(name, user_id, state_id, category_id) values (1, 1, 1, 1);

insert into comments(comment, item_id) values ('Пофиксить баг', 1);

insert into attachments(attach, item_id) values ('D:\Images\bugScreenshot.png', 1);

insert into rule_to_roles(role_id, rules_id) values (1, 1);