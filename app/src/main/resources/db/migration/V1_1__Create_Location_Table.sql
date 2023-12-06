create table location (
    id   SERIAL,
    client_id SERIAL,
    name varchar(255),
    constraint location_pk primary key (id),
    constraint client_fk foreign key (client_id) references client(id)
);