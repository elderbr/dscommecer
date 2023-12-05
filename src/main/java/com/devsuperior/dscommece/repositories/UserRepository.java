package com.devsuperior.dscommece.repositories;

import com.devsuperior.dscommece.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
