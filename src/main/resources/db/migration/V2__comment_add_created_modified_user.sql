alter table comment add created timestamp;
alter table comment add modified timestamp;

alter table comment rename column author to user_name;
alter table comment add first_name varchar(255);
alter table comment add last_name varchar(255);

