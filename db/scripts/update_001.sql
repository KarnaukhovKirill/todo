create table users (
    id serial primary key,
    name varchar(150),
    email varchar(100) not null unique,
    password text,
    created timestamp
)

create table tasks (
    id serial primary key,
    description text,
    created timestamp,
    done boolean,
    user_id int references users(id)
);