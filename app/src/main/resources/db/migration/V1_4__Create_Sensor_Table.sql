create table sensor (
                      id SERIAL,
                      name varchar(255),
                      sensor_type varchar(255),
                      room_id SERIAL,
                      location_id SERIAL,
                      constraint sensor_pk primary key (id),
                      constraint room_fk foreign key (room_id) references room(id)
);