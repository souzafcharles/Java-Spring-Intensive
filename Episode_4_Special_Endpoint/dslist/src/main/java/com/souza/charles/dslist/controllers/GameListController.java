package com.souza.charles.dslist.controllers;

import com.souza.charles.dslist.dto.GameListDTO;
import com.souza.charles.dslist.dto.GameMinDTO;
import com.souza.charles.dslist.dto.ReplacementDTO;
import com.souza.charles.dslist.services.GameListService;
import com.souza.charles.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll() {
        List<GameListDTO> result = gameListService.findAll();
        return result;
    }

    @RequestMapping(value = "/{listId}/games")
    @GetMapping
    public List<GameMinDTO> findByList(@PathVariable Long listId) {
        List<GameMinDTO> result = gameService.findByList(listId);

        return result;
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}
