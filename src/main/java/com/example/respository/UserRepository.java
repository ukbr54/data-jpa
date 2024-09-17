package com.example.respository;

import com.example.entity.Users;
import com.example.projection.UserInfo;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);
    List<Users> findByOrderByCreatedAtAsc(Limit limit);
    /**
     * In projection, we can use record, simple java class and interface.
     * Here we need to provide the full package name if we are using @Query annotation.
     * And here we are using LEFT JOIN only because we are not fetching any association data.
     * If we need to fetch the association data then we will use LEFT JOIN FETCH
     */
    @Query("SELECT new com.example.projection.UserInfo(u.username) FROM Users u LEFT JOIN u.photos p WHERE p.id IS NULL")
    List<UserInfo> findByUsersNeverPostedPhoto();
}
