package com.souza.charles.gamelist.repositories;
 /*
  Course title: Java Spring Intensive
  Instructor: Prof. Dr. Nelio Alves - Dev Superior
  Project done by: Charles Fernandes de Souza
  Date: January 23, 2025
 */

import com.souza.charles.gamelist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
