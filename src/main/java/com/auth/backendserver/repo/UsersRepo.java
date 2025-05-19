package com.auth.backendserver.repo;

import com.auth.backendserver.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, UUID> {
    Optional <Users> findByEmail(String email);
}
