package com.alex.repositories;

import com.alex.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailUserRepository extends JpaRepository<User, Long> {

}
