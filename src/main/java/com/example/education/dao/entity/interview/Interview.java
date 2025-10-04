package com.example.education.dao.entity.interview;

import com.example.education.dao.entity.BaseEntity;
import com.example.education.enums.Level;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@ToString
@Table(name = "interview")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Interview extends BaseEntity {
    private String question;
    private String answer;
    @Enumerated(EnumType.STRING)
    private Level level;
    private String category;
    private String degree;
}
