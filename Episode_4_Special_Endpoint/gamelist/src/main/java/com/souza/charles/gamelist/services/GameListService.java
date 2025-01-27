package com.souza.charles.gamelist.services;
 /*
  Course title: Java Spring Intensive
  Instructor: Prof. Dr. Nelio Alves - Dev Superior
  Project done by: Charles Fernandes de Souza
  Date: January 26, 2025
 */
import com.souza.charles.gamelist.dto.GameListDTO;
import com.souza.charles.gamelist.entities.GameList;
import com.souza.charles.gamelist.projections.GameMinProjection;
import com.souza.charles.gamelist.repositories.GameListRepository;
import com.souza.charles.gamelist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class GameListService {

  @Autowired
  private GameListRepository gameListRepository;

  @Autowired
  private GameRepository gameRepository;

  @Transactional(readOnly = true)
  public List < GameListDTO > findAll() {
    List < GameList > result = gameListRepository.findAll();
    return result.stream().map(GameListDTO::new).toList();
  }

  @Transactional
  public void move(Long listId, int sourceIndex, int destinationIndex) {
    List < GameMinProjection > list = gameRepository.searchByList(listId);
    if (sourceIndex < 0 || sourceIndex >= list.size() || destinationIndex < 0 || destinationIndex >= list.size()) {
      throw new IllegalArgumentException("Invalid source or destination index");
    }
    GameMinProjection object = list.remove(sourceIndex);
    list.add(destinationIndex, object);
    int min = Math.min(sourceIndex, destinationIndex);
    int max = Math.max(sourceIndex, destinationIndex);
    for (int i = min; i <= max; i++) {
      gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
    }
  }
}
