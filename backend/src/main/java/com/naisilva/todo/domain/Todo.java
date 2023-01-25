package com.naisilva.todo.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
public class Todo {
    private Integer id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataParaFinalizar;
    private Boolean finalizado = false;

    public Todo(){
        super();
    }

    public Todo(Integer id, String titulo, String descricao, LocalDateTime dataParaFinalizar, Boolean finalizado) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataParaFinalizar = dataParaFinalizar;
        this.finalizado = finalizado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        return id.equals(todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
