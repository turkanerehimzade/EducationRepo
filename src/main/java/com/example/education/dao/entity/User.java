package com.example.education.dao.entity;

import com.example.education.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@Entity
@ToString
@Table(name = "users")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    @Column(name = "role_name")
    private String roleName;

}
