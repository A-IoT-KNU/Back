package com.knu.app.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("client")
public class ClientEntity {
    @Id
    Integer id;

    String email;
}
