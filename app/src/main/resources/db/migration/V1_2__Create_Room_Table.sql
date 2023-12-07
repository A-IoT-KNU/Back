create table room (
    id SERIAL,
    location_id SERIAL,
    name varchar(255),
    constraint room_pk primary key (id),
    constraint location_fk foreign key (location_id) references location(id)
);