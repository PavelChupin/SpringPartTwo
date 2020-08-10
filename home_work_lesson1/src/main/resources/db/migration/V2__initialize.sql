drop table if exists users;
create table users (
  id                    bigserial IDENTITY ,
  phone                 VARCHAR(30) not null UNIQUE,
  password              VARCHAR(80) not null,
  login                 VARCHAR(80) not null UNIQUE,
  email                 VARCHAR(50) UNIQUE,
  first_name            VARCHAR(50),
  last_name             VARCHAR(50),
  block                 boolean,
  PRIMARY KEY (id)
);

drop table if exists roles;
create table roles (
  id                    serial,
  name                  VARCHAR(50) not null,
  primary key (id)
);

drop table if exists users_roles;
create table users_roles (
  user_id               INT NOT NULL,
  role_id               INT NOT NULL,
  primary key (user_id, role_id),
  FOREIGN KEY (user_id)
  REFERENCES users (id),
  FOREIGN KEY (role_id)
  REFERENCES roles (id)
);

insert into roles (name)
values
('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

insert into users (phone, password, login , first_name, last_name, email,block )
values
('+79137382899','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','admin','Pavel','Chupin','pavel.chupin@gmail.com',FALSE);

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(1, 3);

insert into users (phone, password, login , first_name, last_name, email,block )
values
('+791373828','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','user1','user1_FirstName','user1_Last_Name','user1@gmail.com',FALSE );

insert into users_roles (user_id, role_id)
values
(2, 2);

insert into users (phone, password, login , first_name, last_name, email,block )
values
('+791373899','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','user2','user2_FirstName','user2_Last_Name','user2@gmail.com',FALSE );

insert into users_roles (user_id, role_id)
values
(3, 1);
