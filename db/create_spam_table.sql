create table if not exists spam_users
(
    id serial primary key,
    name varchar(255),
    email varchar(255)
)