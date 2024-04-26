create table messages
(
    id serial primary key,
    sender_id bigint references users(id),
    receiver_id bigint references users(id),
    content text,
    created timestamp,
    unique (sender_id, receiver_id)
);