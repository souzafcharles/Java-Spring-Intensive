package com.souza.charles.gamelist.controllers;
/*
  Course title: Java Spring Intensive
  Instructor: Prof. Dr. Nelio Alves - Dev Superior
  Project done by: Charles Fernandes de Souza
  Date: January 26, 2025
 */

import com.souza.charles.gamelist.dto.GameListDTO;
import com.souza.charles.gamelist.dto.GameMinDTO;
import com.souza.charles.gamelist.dto.ReplacementDTO;
import com.souza.charles.gamelist.services.GameListService;
import com.souza.charles.gamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByGameList(@PathVariable Long listId) {
        return gameService.findByGameList(listId);
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}