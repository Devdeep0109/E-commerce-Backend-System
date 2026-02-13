package com.example.E_commerce.service;

import com.example.E_commerce.dto.userDto.UserLoginRequest;
import com.example.E_commerce.dto.userDto.UserRegisterRequest;
import com.example.E_commerce.exception.ApplicationException;
import com.example.E_commerce.exception.InvalidCredentialsException;
import com.example.E_commerce.exception.UserNotFoundException;
import com.example.E_commerce.model.User;
import com.example.E_commerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public ResponseEntity<?> registerUser(UserRegisterRequest userRequest) {

        if (repo.findByEmail(userRequest.getEmail()) != null) {
            throw new ApplicationException("Email is already registered!");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        repo.save(user);
        return new ResponseEntity<>("User registered successfuly!",HttpStatus.OK);
    }

    public ResponseEntity<?> loginUser(UserLoginRequest userRequest) {

            String emailFromUser = userRequest.getEmail();
            String passwordFromUser = userRequest.getPassword();

            User userFromDb = repo.findByEmail(emailFromUser);

            if (userFromDb == null) {
                throw new UserNotFoundException("User not found with email: " + emailFromUser);
            }

            if (!userFromDb.getPassword().equals(passwordFromUser)) {
                throw new InvalidCredentialsException("Invalid email or password");
            }
            return new ResponseEntity<>("User login successfully", HttpStatus.OK);
    }
}
