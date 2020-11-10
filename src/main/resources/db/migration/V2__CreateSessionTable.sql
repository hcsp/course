CREATE TABLE session
(
    id      serial PRIMARY KEY,
    cookie  VARCHAR(50) UNIQUE NOT NULL,
    user_id INT                NOT NULL
);

INSERT INTO session(cookie, user_id)
values ('test_user_1', 1);
INSERT INTO session(cookie, user_id)
values ('test_user_2', 2);
INSERT INTO session(cookie, user_id)
values ('test_user_3', 3);

alter sequence session_id_seq restart with 4;
