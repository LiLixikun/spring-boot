package com.example.demo1.repository;

import com.example.demo1.domain.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user,Integer> {
}
