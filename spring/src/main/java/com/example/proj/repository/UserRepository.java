package com.example.proj.repository;

import com.example.proj.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public User save(User user);

}
