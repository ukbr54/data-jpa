package com.example.respository;

import com.example.entity.Users;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);
    List<Users> findByOrderByCreatedAtAsc(Limit limit);
}
