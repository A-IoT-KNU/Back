package com.knu.app.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("sensor")
public class Sensor {

    @Id
    Integer id;

    @Column("name")
    String sensorName;

    @Column("sensor_type")
    String sensorTypes;

    @Column("location_id")
    Integer locationId;

    @Column("room_id")
    Integer roomId;

}
