package com.example.education.dao.entity.interview;

import com.example.education.dao.entity.BaseEntity;
import jakarta.persistence.Entity;
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
    private String category;
    private String degree;
}
