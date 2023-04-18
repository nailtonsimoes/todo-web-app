package com.naisilva.todo.repositories;

import com.naisilva.todo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    @Query("SELECT u from User u JOIN FETCH u.roles where u.name = :name")
    Optional<User> findByNameFetchRoles(@Param("name") String name);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}