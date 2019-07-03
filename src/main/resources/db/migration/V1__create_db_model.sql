create table comment (uuid uuid not null, attached_entity_id uuid not null, attribute_name varchar(255), author varchar(255), content varchar(2000), primary key (uuid));
