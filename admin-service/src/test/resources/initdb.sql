create schema test;

create table good
(
    id varchar primary key,
    type varchar(255),
    manufacturer varchar(255),
    price int,
    name varchar(255),
    release_date timestamp
);
