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

CREATE table image
(
    id bigserial primary key,
    good_id bigint,
    s3_uri varchar,

    FOREIGN KEY(good_id) REFERENCES good(id)
);
