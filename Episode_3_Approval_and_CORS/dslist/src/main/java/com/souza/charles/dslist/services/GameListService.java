package com.souza.charles.dslist.services;

import com.souza.charles.dslist.dto.GameListDTO;
import com.souza.charles.dslist.entities.GameList;
import com.souza.charles.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class GameListService {

    @Autowired
    public GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }
}
