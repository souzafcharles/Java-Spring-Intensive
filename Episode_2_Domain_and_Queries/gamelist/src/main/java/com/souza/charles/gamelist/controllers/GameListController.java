package com.souza.charles.gamelist.controllers;
/*
  Course title: Java Spring Intensive
  Instructor: Prof. Dr. Nelio Alves - Dev Superior
  Project done by: Charles Fernandes de Souza
  Date: January 24, 2025
 */

import com.souza.charles.gamelist.dto.GameListDTO;
import com.souza.charles.gamelist.dto.GameMinDTO;
import com.souza.charles.gamelist.services.GameListService;
import com.souza.charles.gamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameListService gameListService;

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

    @GetMapping(value = "/{id}")
    public GameListDTO findById(@PathVariable Long id){
        return gameListService.findById(id);
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findGames(@PathVariable Long listId) {
        List<GameMinDTO> result = gameService.findByGameList(listId);
        return result;
    }
}