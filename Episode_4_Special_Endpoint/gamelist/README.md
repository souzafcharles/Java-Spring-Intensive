# Episode 4: Special Endpoint
## Project Requirements and Configurations:
### 1. Checking the H2 Database Before Replacing Item Positions:
- Verify the result query to visualize the positions in the `TB_BELONGING`:
```sql
SELECT TB_BELONGING .*, TB_GAME.TITLE FROM TB_BELONGING
INNER JOIN TB_GAME ON TB_GAME.ID = TB_BELONGING.GAME_ID
WHERE LIST_ID = 2
ORDER BY POSITION
```
- The query result table before replacement:

| POSITION | GAME_ID | LIST_ID | TITLE                    |
|----------|---------|---------|--------------------------|
| 0        | 6       | 2       | Super Mario World        |
| 1        | 7       | 2       | Hollow Knight            |
| 2        | 8       | 2       | Ori and the Blind Forest |
| 3        | 9       | 2       | Cuphead                  |
| 4        | 10      | 2       | Sonic CD                 |
***
### 2. Defining the `ReplacementDTO` Entity:
#### 2.1 Requirements for the `ReplacementDTO` Entity Class:
- Create the `ReplacemntDTO` Class;
- Define basic attributes: `sourceIndex` and `destinationIndex`.
- Implement Getters and Setters.
- Ensure the class is Serializable.
***
### 3. Updating the `GameListRepository` Class:
- Define a custom native query to update and order items by their new position in the list:
```java
public interface GameListRepository extends JpaRepository<GameList, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
    void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
}
```
#### 3.1 Import Statements:
#### 3.1.1 Interface Definition:
- The `GameListRepository` interface extends `JpaRepository`, making `CRUD` operations and custom queries available for the `GameList` entity.
#### 3.1.2 Custom Query Method:
- The updateBelongingPosition method is defined with @Modifying and @Query annotations to perform a custom update operation in the database;
- `@Modifying Annotation`: Declares that the query being run is an `update` or `delete` operation;
- `@Query` Annotation:
  - `nativeQuery` = `true`: Indicates that the query is a native SQL query;
  - `value` = The SQL query to update the position of a game in the list by its listId and gameId.
#### 3.1.3 Method Parameters:
- Long `listId`: The ID of the list containing the game;
- Long `gameId`: The ID of the game whose position needs updating;
- Integer `newPosition`: The new position for the game in the list.
***
### 4. Implementing Item Position Replacement in `GameListService`:
#### 4.1 Replacement Logic:
```java

@Transactional(readOnly = true)
public void move(Long listId, int sourceIndex, int destinationIndex) {
    List<GameMinProjection> list = gameRepository.searchByList(listId);
    GameMinProjection object = list.remove(sourceIndex);
    list.add(destinationIndex, object);
    int min = Math.min(sourceIndex, destinationIndex);
    int max = Math.max(sourceIndex, destinationIndex);
    for (int i = min; i <= max; i++) {
        gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
    }
}
```
#### 4.2 Import Statements:
#### 4.2.1 `@Transactional`(readOnly = true):
- Indicates that this method is transactional, meaning all the operations within it are treated as a single unit of work;
- `readOnly = true` specifies that the method is a read-only transaction, generally used to optimize performance for read operations.
#### 4.2.2 public void `move`(Long listId, int sourceIndex, int destinationIndex):
- Defines the method `move` which takes three parameters:
  - `listId`: The ID of the game list;
  - `sourceIndex`: The starting position of the item to move;
  - `destinationIndex`: The new position to which the item should be moved.
#### 4.2.3 List<`GameMinProjection`> list = gameRepository.searchByList(`listId`);:
- Retrieves the `GameList` for the given `listId` from the `GameRepository` and assigns it to the variable list.
#### 4.2.4 `GameMinProjection` object = list.`remove`(sourceIndex);:
- Removes the game object at the `sourceIndex` from the list and assigns it to the variable `object`.
#### 4.2.5 list.`add`(destinationIndex, object);:
- Adds the removed game object (`object`) to the list at the `destinationIndex`.
#### 4.2.6 int min = Math.`min`(sourceIndex, destinationIndex);:
- Calculates the minimum value between `sourceIndex` and `destinationIndex` and assigns it to `min`.
#### 4.2.7 int max = Math.`max`(sourceIndex, destinationIndex);:
- Calculates the maximum value between `sourceIndex` and `destinationIndex` and assigns it to `max`.
#### 4.2.8 for (int i = min; i <= max; i++) {:
- Initiates a loop that runs from the minimum (`min`) to the maximum (`max`) index;
- gameListRepository.`updateBelongingPosition`(listId, list.get(i).getId(), i);:
  - Within the loop, updates the position of each game in the list to reflect the changes made. Calls the `updateBelongingPosition` method of `gameListRepository` to update the position of the game with the respective `listId`, `game ID`, and new index (`i`).
***
### 5. Creating a Special Endpoint in `GameListController`:
#### 5.1 Implementing the Endpoint:
```java

@PostMapping(value = "/{listId}/replacement")
public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
    gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
}
```
#### 5.1 Import Statements:
- `@PathVariable` Long listId: Binds the listId from the URL path to this parameter;
- `@RequestBody` `ReplacementDTO` body: Binds the request body JSON payload to a `ReplacementDTO` object;
- Calls the `move` method of the `gameListService` with the `listId`, `sourceIndex`, and `destinationIndex` from the `ReplacementDTO` object.
***
### 6. Setting Up the RESTful API for HTTP Methods (Non-Idempotent):
```JSON
POST Request http://localhost:8080/lists/2/replacement 
Body -> raw -> JSON
```
```json
{
  "sourceIndex": 3,
  "destinationIndex": 1
}
```
***
### 7. Checking the H2 Database After Replacing Item Positions:
- VVerify the result query to visualize the updated positions in the `TB_BELONGING`:
```sql
SELECT TB_BELONGING .*, TB_GAME.TITLE FROM TB_BELONGING
INNER JOIN TB_GAME ON TB_GAME.ID = TB_BELONGING.GAME_ID
WHERE LIST_ID = 2
ORDER BY POSITION
```
- The query result table after replacement:

| POSITION | GAME_ID | LIST_ID | TITLE                    |
|----------|---------|---------|--------------------------|
| 0        | 6       | 2       | Super Mario World        |
| 1        | 9       | 2       | Cuphead                  |
| 2        | 7       | 2       | Hollow Knight            |
| 3        | 8       | 2       | Ori and the Blind Forest |
| 4        | 10      | 2       | Sonic CD                 |
***
### Steps Checklist:
:ballot_box_with_check: Verify the initial positions in the H2 database;<br/>
:ballot_box_with_check: Define the ReplacementDTO entity class;<br/>
:ballot_box_with_check: Implement custom query in the GameListRepository;<br/>
:ballot_box_with_check: Implement logic for replacing item positions in GameListService;<br/>
:ballot_box_with_check: Implement the endpoint in GameListController;<br/>
:ballot_box_with_check: Set up the RESTful API for HTTP methods;<br/>
:ballot_box_with_check: Verify the updated positions in the H2 database.
