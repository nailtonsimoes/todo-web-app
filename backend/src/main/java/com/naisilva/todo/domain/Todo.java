package com.naisilva.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_TODO")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateForFinalize;

    private Boolean finshed = false;

    @ManyToOne
    private User user;

    public Todo(Long id, String title, String description, Date dateForFinilze, Boolean finshed){
        this.title = title;
        this.description = description;
        this.dateForFinalize = dateForFinilze;
        this.finshed = finshed;
    }
}
