package com.knu.app.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("location")
public class Location {
    @Id
    Integer id;

    Integer clientId;

    String name;
}
