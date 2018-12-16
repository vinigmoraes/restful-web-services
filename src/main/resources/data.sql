create table if not exists  user (
id integer not null,
birth_date timestamp,
name varchar(255),
primary key (id));


insert into user values(1, sysdate(), 'Gabriel');
insert into user values(2, sysdate(), 'Joao');
insert into user values(3, sysdate(), 'Jose');
