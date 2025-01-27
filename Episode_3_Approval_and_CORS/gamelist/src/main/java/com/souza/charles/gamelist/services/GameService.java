package com.souza.charles.gamelist.services;
 /*
  Course title: Java Spring Intensive
  Instructor: Prof. Dr. Nelio Alves - Dev Superior
  Project done by: Charles Fernandes de Souza
  Date: January 25, 2025
 */

import com.souza.charles.gamelist.dto.GameDTO;
import com.souza.charles.gamelist.dto.GameMinDTO;
import com.souza.charles.gamelist.entities.Game;
import com.souza.charles.gamelist.projections.GameMinProjection;
import com.souza.charles.gamelist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameDTO findById(@PathVariable Long id) {
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByGameList(Long listId) {
        List<GameMinProjection> games = gameRepository.searchByList(listId);
        return games.stream().map(GameMinDTO::new).toList();
    }
}