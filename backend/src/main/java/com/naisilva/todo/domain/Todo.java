package com.naisilva.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_TODO")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String titulo;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataParaFinalizar;
    private Boolean finalizado = false;

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
