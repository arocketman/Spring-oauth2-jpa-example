package com.github.arocketman.repositories;

import com.github.arocketman.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andreuccio on 13/04/2017.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
