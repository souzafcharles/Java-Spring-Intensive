package com.souza.charles.gamelist.dto;
/*
 Course title: Java Spring Intensive
 Instructor: Prof. Dr. Nelio Alves - Dev Superior
 Project done by: Charles Fernandes de Souza
 Date: January 26, 2025
*/
import java.io.Serial;
import java.io.Serializable;

public class ReplacementDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer sourceIndex;
    private Integer destinationIndex;

    public Integer getSourceIndex() {
        return sourceIndex;
    }

    public void setSourceIndex(Integer sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    public Integer getDestinationIndex() {
        return destinationIndex;
    }

    public void setDestinationIndex(Integer destinationIndex) {
        this.destinationIndex = destinationIndex;
    }
}