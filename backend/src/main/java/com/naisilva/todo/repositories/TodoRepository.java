package com.naisilva.todo.repositories;

import com.naisilva.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    /*
    @Query("SELECT obj FROM Todo obj WHERE obj.finalizado = false ORDER BY obj.dataParaFinalizar")
    List<Todo> findAllOpen();
    */

   /* @Query("SELECT obj FROM Todo obj WHERE obj.finalizado = true ORDER BY obj.dataParaFinalizar")
    List<Todo> findAllClose(); */

    List<Todo> findByUserId(Long userId);

    List<Todo> findByUserEmail(String email);

    List<Todo> findByUser(String name);
}
