package com.naisilva.todo.exceptions;

import lombok.Data;

import java.io.Serializable;

@Data
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private long timesTemp;
    private Integer status;
    private String message;

    public StandardError(long timesTemp, int status, String message) {
        super();
        this.timesTemp = timesTemp;
        this.status = status;
        this.message = message;
    }
}
