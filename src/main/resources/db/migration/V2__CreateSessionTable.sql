CREATE TABLE session
(
    id      serial PRIMARY KEY,
    cookie  VARCHAR(50) UNIQUE NOT NULL,
    user_id INT                NOT NULL
);

INSERT INTO session(cookie, user_id)
values ('student_user_cookie', 1);
INSERT INTO session(cookie, user_id)
values ('teacher_user_cookie', 2);
INSERT INTO session(cookie, user_id)
values ('admin_user_cookie', 3);

alter sequence session_id_seq restart with 4;
