package com.knu.app.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@Builder
@Table("client")
public class Client {
    @Id
    Integer id;

    String email;
}
