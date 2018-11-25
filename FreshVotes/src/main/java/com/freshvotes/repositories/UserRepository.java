package com.freshvotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshvotes.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

  User findByUsername(String username);

}
