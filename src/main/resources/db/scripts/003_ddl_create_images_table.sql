create table images
(
    id serial primary key,
    name text not null,
    path text not null unique,
	post_id bigint not null references posts(id)
);