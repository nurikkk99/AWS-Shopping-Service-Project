create schema test;

create table good
(
    id varchar primary key,
    type varchar(255),
    manufacturer varchar(255),
    price numeric,
    name varchar(255),
    release_date timestamp
);
