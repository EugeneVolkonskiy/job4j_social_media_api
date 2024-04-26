create table subscribers
(
    id serial primary key,
    user_id_from bigint references users(id),
    user_id_to bigint references users(id),
    unique (user_id_from, user_id_to)
);