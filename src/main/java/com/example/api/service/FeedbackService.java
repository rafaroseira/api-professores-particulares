package com.example.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.api.dto.FeedbackDTO;
import com.example.api.dto.PostFeedbackDTO;
import com.example.api.exceptions.RoleException;
import com.example.api.exceptions.UserNotFoundException;
import com.example.api.model.User;
import com.example.api.model.TeacherFeedback;
import com.example.api.model.TeacherFeedbackId;
import com.example.api.repository.FeedbackRepository;
import com.example.api.repository.UserRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void postFeedback(PostFeedbackDTO dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User student = userRepository.findByEmail(auth.getName());
        User teacher = userRepository.findById(dto.teacherId().intValue());

        if(usersExist(student, teacher)){
            verifyUsersRoles(student, teacher);
            TeacherFeedbackId id = new TeacherFeedbackId(student.getId(), teacher.getId());
            feedbackRepository.save(new TeacherFeedback(id, student, teacher, dto.comment(), dto.rating()));
        }
        
    }

    @Transactional
    public List<FeedbackDTO> getTeacherFeedback(int id){
        return feedbackRepository.findByTeacherId(id);
    }

    private boolean usersExist(User student, User teacher){

        if(student == null || teacher == null){
            throw new UserNotFoundException();
        }

        return true;
    }

    private void verifyUsersRoles(User student, User teacher){
        if(!(teacher.getRole().name().equals("ROLE_TEACHER") &&
        student.getRole().name().equals("ROLE_STUDENT"))){
            throw new RoleException();
        }
    }
}