create table posts
(
    id serial primary key,
    title text not null,
    content text not null,
    created timestamp not null,
    user_id bigint references users(id) not null
);