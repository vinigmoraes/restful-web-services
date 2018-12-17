create table if not exists  user (
id integer not null,
birth_date timestamp,
name varchar(255),
primary key (id));


insert into user values(100, sysdate(), 'Gabriel');
insert into user values(102, sysdate(), 'Joao');
insert into user values(103, sysdate(), 'Jose');

insert into post values(101, 'Facebook post', 100);
insert into post values(102, 'Medium post', 102);
insert into post values(103, 'LInkedin post', 103);



