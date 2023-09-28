package com.naisilva.todo.repositories;

import com.naisilva.todo.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
    @Query("SELECT u from UserEntity u JOIN FETCH u.roles where u.name = :name")
    Optional<UserEntity> findByNameFetchRoles(@Param("name") String name);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(Long id);
}