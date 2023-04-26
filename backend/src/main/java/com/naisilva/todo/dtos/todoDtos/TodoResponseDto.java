package com.naisilva.todo.dtos.todoDtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String title;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateForFinalize;
    private boolean finished;

    private Long userId;

    public TodoResponseDto(Long id, String title, String description, Date dateForFinalize, boolean finished) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateForFinalize = dateForFinalize;
        this.finished = finished;
    }
}
