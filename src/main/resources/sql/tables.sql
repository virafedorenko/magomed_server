CREATE TABLE users(
id varchar(64) primary key,
email varchar(64) not null,
password varchar(64) not null,
sname varchar (64) not null
);

CREATE TABLE tracking_objects(
id varchar(64) primary key,
sname varchar(64) not null
);

CREATE TABLE tracking_events(
id varchar(64) primary key,
sname varchar(64) not null,
comment varchar(250),
object_id varchar(64) references tracking_objects(id)
);