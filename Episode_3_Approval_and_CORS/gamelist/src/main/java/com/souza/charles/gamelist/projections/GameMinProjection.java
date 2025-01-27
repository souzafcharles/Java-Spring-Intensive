package com.souza.charles.gamelist.projections;
/*
 Course title: Java Spring Intensive
 Instructor: Prof. Dr. Nelio Alves - Dev Superior
 Project done by: Charles Fernandes de Souza
 Date: January 24, 2025
*/
public interface GameMinProjection {
    Long getId();
    String getTitle();
    Integer getGameYear();
    String getImgUrl();
    String getShortDescription();
    Integer getPosition();
}