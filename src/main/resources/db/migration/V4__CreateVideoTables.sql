CREATE TABLE course
(
    id                  serial PRIMARY KEY,
    name                VARCHAR(100) NOT NULL,
    description         TEXT,
    teacher_name        VARCHAR(50)  NOT NULL,
    teacher_description VARCHAR(50)  NOT NULL,
    price               INT,
    created_at          TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at          TIMESTAMP    NOT NULL DEFAULT now(),
    status              VARCHAR(10)  NOT NULL DEFAULT 'OK'
);

CREATE TABLE video
(
    id          serial PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    course_id   INT,
    created_at  TIMESTAMP    NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT now(),
    status      VARCHAR(10)  NOT NULL DEFAULT 'OK'
);