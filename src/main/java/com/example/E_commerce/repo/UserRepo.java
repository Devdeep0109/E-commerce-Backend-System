package com.example.E_commerce.repo;

import com.example.E_commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {


    User findByEmail(String email);
}
