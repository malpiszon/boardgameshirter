ALTER TABLE users ALTER COLUMN password TYPE VARCHAR(60);

CREATE TABLE roles (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users_roles (
  user_id BIGSERIAL,
  role_id BIGSERIAL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT FK_users_id FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT FK_roles_id FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE privileges (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE roles_privileges (
  role_id BIGSERIAL,
  privilege_id BIGSERIAL,
  PRIMARY KEY (role_id, privilege_id),
  CONSTRAINT FK_roles_id FOREIGN KEY (role_id) REFERENCES roles(id),
  CONSTRAINT FK_privileges_id FOREIGN KEY (privilege_id) REFERENCES privileges(id)
);