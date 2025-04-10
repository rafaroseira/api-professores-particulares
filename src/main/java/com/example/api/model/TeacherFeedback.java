package com.example.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers_feedbacks")
public class TeacherFeedback {

    @EmbeddedId
    private TeacherFeedbackId id;

    @ManyToOne //FetchType lazy??
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne //FetchType lazy??
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Column(length = 500, nullable = false)
    private String comment;

    @Column(nullable = false)
    private byte rating;

    public TeacherFeedbackId getId() {
        return id;
    }

    public void setId(TeacherFeedbackId id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public TeacherFeedback(TeacherFeedbackId id, User student, User teacher, String comment, byte rating) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
        this.comment = comment;
        this.rating = rating;
    }

    public TeacherFeedback() {
    }

}
