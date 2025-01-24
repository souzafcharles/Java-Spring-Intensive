package com.souza.charles.gamelist.services;
 /*
  Course title: Java Spring Intensive
  Instructor: Prof. Dr. Nelio Alves - Dev Superior
  Project done by: Charles Fernandes de Souza
  Date: January 23, 2025
 */
import com.souza.charles.gamelist.dto.GameMinDTO;
import com.souza.charles.gamelist.entities.Game;
import com.souza.charles.gamelist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> gameFindAll = gameRepository.findAll();
        return gameFindAll.stream().map(GameMinDTO::new).toList();
    }
}