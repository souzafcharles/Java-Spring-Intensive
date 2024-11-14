# Project Requirements and Configurations
## Episode 4: Special Endpoint
### Steps:
#### Special endpoint:
- Improvement the design and implementation of a special endpoint;
- Set up HTTP verb and idempotency.

### Relocation of item positions
```java
@PostMapping(value = "/{listId}/replacement")
public void move(@PathVariable Long listId, @RequestBody ReplacementDTObody) {
  gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
}
```
### Relocation of item positions
```java
@Transactional
public void move(Long listId, int sourceIndex, int destinationIndex) {
  List < GameMinProjection > list = gameRepository.searchByList(listId);
  GameMinProjection obj = list.remove(sourceIndex);
  list.add(destinationIndex, obj);
  int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
  int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
  for (int i = min; i <= max; i++) {
    gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
  }
}
```

### Relocation of item positions
```java
@Modifying
@Query(nativeQuery = true,
    value = "UPDATE tb_belonging SET position = :newPosition
    WHERE list_id = :listId AND game_id = :gameId ")
    void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
```