create table news_feed
(
    id serial primary key,
    user_id bigint references users(id),
    post_id bigint references posts(id),
    created timestamp,
    unique (user_id, post_id)
);