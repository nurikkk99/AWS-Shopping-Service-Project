create schema test;

create table good
(
    id bigserial primary key,
    type varchar,
    manufacturer varchar,
    price numeric,
    name varchar,
    release_date timestamp
);
