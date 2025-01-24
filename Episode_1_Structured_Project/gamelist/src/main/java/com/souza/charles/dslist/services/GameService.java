package com.souza.charles.dslist.services;

import com.souza.charles.dslist.dto.GameMinDTO;
import com.souza.charles.dslist.entities.Game;
import com.souza.charles.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    public GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

}

