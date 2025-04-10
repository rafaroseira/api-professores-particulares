package com.example.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.api.dto.FeedbackDTO;
import com.example.api.model.TeacherFeedback;
import com.example.api.model.TeacherFeedbackId;

public interface FeedbackRepository extends JpaRepository<TeacherFeedback, TeacherFeedbackId>{

    @Query(value = "SELECT new com.example.api.dto.FeedbackDTO(tf.comment,tf.rating,tf.student.name) from TeacherFeedback tf where tf.teacher.id = :id")
    List<FeedbackDTO> findByTeacherId(int id);
}
