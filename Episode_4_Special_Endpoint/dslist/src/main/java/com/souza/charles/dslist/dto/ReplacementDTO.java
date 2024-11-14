package com.souza.charles.dslist.dto;

public class ReplacementDTO {

    private Integer SourceIndex;
    private Integer DestinationIndex;

    public Integer getDestinationIndex() {
        return DestinationIndex;
    }

    public void setDestinationIndex(Integer destinationIndex) {
        DestinationIndex = destinationIndex;
    }

    public Integer getSourceIndex() {
        return SourceIndex;
    }

    public void setSourceIndex(Integer sourceIndex) {
        SourceIndex = sourceIndex;
    }
}
