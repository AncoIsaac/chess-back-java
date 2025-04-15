package com.isaacAnco.chessv2.repository.user;

import com.isaacAnco.chessv2.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
}
