CREATE TABLE session (
    id serial PRIMARY KEY,
    cookie VARCHAR (50) UNIQUE NOT NULL,
    user_id INT NOT NULL
)