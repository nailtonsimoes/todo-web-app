package com.naisilva.todo.repositories;

import com.naisilva.todo.domain.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    /*
    @Query("SELECT obj FROM TodoEntity obj WHERE obj.finalizado = false ORDER BY obj.dataParaFinalizar")
    List<TodoEntity> findAllOpen();
    */

   /* @Query("SELECT obj FROM TodoEntity obj WHERE obj.finalizado = true ORDER BY obj.dataParaFinalizar")
    List<TodoEntity> findAllClose(); */

    List<TodoEntity> findByUserId(Long id);

    List<TodoEntity> findByUserEmail(String email);

    List<TodoEntity> findByUserName(String name);
}
