# Project Requirements and Configurations
## Episode 4: Special Endpoint
### Steps:
#### Special endpoint:
- Improvement the design and implementation of a special endpoint;
- Replacement of item positions logic;
- Set up HTTP verb and idempotency.

## Code Snippets

### GameListController (Replacement of item positions)
```java
@PostMapping(value = "/{listId}/replacement")
public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
  gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
}
```
### GameListService Replacement of item positions)
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

### GameListRepository (Replacement of item positions)

```java
@Modifying
@Query(nativeQuery = true, value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
```

### H2 Query 
```sql
SELECT TB_BELONGING .*, TB_GAME.TITLE FROM TB_BELONGING
INNER JOIN TB_GAME ON TB_GAME.ID = TB_BELONGING.GAME_ID
WHERE LIST_ID = 2
ORDER BY POSITION
```

### HTTP Methods

```JSON
POST http://localhost:8080/lists/2/replacement 
Body -> raw -> JSON
{
"sourceIndex": 3,
"destinationIndex": 1
}
```