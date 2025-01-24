package com.souza.charles.dslist.repositories;

import com.souza.charles.dslist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
