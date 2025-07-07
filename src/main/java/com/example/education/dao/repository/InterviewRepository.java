package com.example.education.dao.repository;

import com.example.education.dao.entity.interview.Interview;
import com.example.education.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByLevel(Level level);

}
