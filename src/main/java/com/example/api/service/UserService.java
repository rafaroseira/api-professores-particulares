package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.api.dto.CreateUserDTO;
import com.example.api.dto.UpdatePasswordDTO;
import com.example.api.dto.UpdateUserInfoDTO;
import com.example.api.dto.UserDTO;
import com.example.api.exceptions.EmailAlreadyExistsException;
import com.example.api.exceptions.RoleException;
import com.example.api.exceptions.UserNotFoundException;
import com.example.api.model.Role;
import com.example.api.model.User;
import com.example.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Transactional
    public void registerUser(CreateUserDTO dto){

        isEmailAvailable(dto.email());
        isRoleValid(dto.role());

        String encodedPassword = bcrypt.encode(dto.password());
    
        userRepository.save(new User(dto.name(), dto.lastName(), dto.email(), encodedPassword, Role.valueOf(dto.role()), null, null, null));
            
    }

    @Transactional
    public void updateInfo(UpdateUserInfoDTO dto){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        if(user == null){throw new UserNotFoundException();}
        
        user.setName(dto.name());
        user.setLastName(dto.lastName());
        userRepository.save(user);
    }

    @Transactional
    public void updatePassword(UpdatePasswordDTO dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        if(user == null){throw new UserNotFoundException();}

        if(bcrypt.matches(dto.currentPassword(), user.getPassword())){
            user.setPassword(bcrypt.encode(dto.newPassword()));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Senha atual está incorreta!");
        }
    }

    public UserDTO retrieveUser(int id){

        User user = userRepository.findById(id);
        if(user == null){throw new UserNotFoundException();}

        return new UserDTO(user.getName(), user.getLastName(), user.getUsername());
    }

    private boolean isEmailAvailable(String email){
        User user = userRepository.findByEmail(email);

        if(user == null){
            return true;
        } else {
            throw new EmailAlreadyExistsException();
        }
    }

    private boolean isRoleValid(String role){
        switch(role){
            case "ROLE_STUDENT":return true;
            case "ROLE_TEACHER":return true;
            default: throw new RoleException();
        }
    }
}
