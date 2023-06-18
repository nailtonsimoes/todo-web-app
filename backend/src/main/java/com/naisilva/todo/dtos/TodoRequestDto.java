package com.naisilva.todo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {
    @JsonIgnore
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateForFinalize;

    private Boolean finished = false;

}
