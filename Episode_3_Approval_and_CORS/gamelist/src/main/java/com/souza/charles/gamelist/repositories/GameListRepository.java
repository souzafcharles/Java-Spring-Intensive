package com.souza.charles.gamelist.repositories;
 /*
  Course title: Java Spring Intensive
  Instructor: Prof. Dr. Nelio Alves - Dev Superior
  Project done by: Charles Fernandes de Souza
  Date: January 24, 2025
 */

import com.souza.charles.gamelist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long> {
}
