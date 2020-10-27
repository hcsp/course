CREATE TABLE "user" (
	id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	encrypted_password VARCHAR ( 50 ) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT now(),
	updated_at TIMESTAMP NOT NULL DEFAULT now(),
	status VARCHAR(10) NOT NULL DEFAULT 'OK'
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    status VARCHAR(10) NOT NULL DEFAULT 'OK'
);

INSERT INTO role(id, name)values(1, '学生');
INSERT INTO role(id,name)values(2, '老师');
INSERT INTO role(id,name)values(3, '管理员');

CREATE TABLE user_role(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    status VARCHAR(10) NOT NULL DEFAULT 'OK'
);

INSERT INTO "user"(id, username, encrypted_password)values(1, 'Student1', '');
INSERT INTO "user"(id, username, encrypted_password)values(2, 'Teacher2', '');
INSERT INTO "user"(id, username, encrypted_password)values(3, 'Admin3', '');
INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);
INSERT INTO user_role(user_id, role_id) VALUES(3,3);

CREATE TABLE permission(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    role_id INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    status VARCHAR(10) NOT NULL DEFAULT 'OK'
);

INSERT INTO permission(name, role_id) values('登录用户', 1);
INSERT INTO permission(name, role_id) values('登录用户', 2);
INSERT INTO permission(name, role_id) values('登录用户', 3);
INSERT INTO permission(name, role_id) values('上传课程', 2);
INSERT INTO permission(name, role_id) values('上传课程', 3);
INSERT INTO permission(name, role_id) values('管理用户', 3);