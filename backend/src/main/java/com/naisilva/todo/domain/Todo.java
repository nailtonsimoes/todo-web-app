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
@Table(name = "todos")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "dateForFinalize")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateForFinalize;

    @Column(name = "finshed")
    private Boolean finshed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    public Todo(Long id, String title, String description, Date dateForFinilze, Boolean finshed){
        this.title = title;
        this.description = description;
        this.dateForFinalize = dateForFinilze;
        this.finshed = finshed;
    }
}
