# Episode 2: Domain and Queries
## Project Requirements and Configurations:
### 1. Relationships:
#### 1.1 Game Domain Entities Model:
![Order Domain Entities Model](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_2_Domain_and_Queries/gamelist/game-model.png)
***
#### 1.2 Object Relational Model:
![Object Relational Model](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_2_Domain_and_Queries/gamelist/object-relational.png)
#### 1.3 Table Relational Model:
***
![Table Relational Model](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_2_Domain_and_Queries/gamelist/table-relational.png)
***
### 2. Belonging and BelongingPK Entities Classes and Many-to-Many Association with Extra Attributes:
#### 2.1 Entity Class Requirements for BelongingPK:
- Create the `BelongingPK` Entity Class;
- Annotate the class with `@Embeddable` to indicate it is an embeddable key class.
- Define `Game` and `GameList` attributes as `@ManyToOne` associations, annotated with `@JoinColumn` to define foreign key columns:
    - game → mapped to game_id.
    - list → mapped to list_id.
- Getters & Setters;
- hashCode & equals;
- Serializable.
#### 2.2 Entity Class Requirements for Belonging:
- Create the `Beloging` Entity Class;
- Annotate the class with `@Entity` and `@Table`(name = "tb_belonging") to map it to the database table;
- Basic Attributes;
- Annotate `@Id` and `@GeneratedValue` for the primary key, and `@Column` for other fields;
- Constructors;
- Getters & Setters;
- hashCode & equals;
- Serializable.
***
### 3. GameDTO Entity,
#### 3.1 Entity Class Requirements for GameDTO:
- Create the `GameDTO` Class;
- Basic Attributes;
- Implement a no-argument constructor;
- Implement a constructor that takes a `Game` entity object and uses `BeanUtils.copyProperties` to initialize the fields.
- Getters & Setters;
- Serializable.
***
### 4. Game Entity, Repository, Service and Resource Classes:
#### 4.1 Game Service Class Requirements for GameDTO and GameMinDTO:
- Use `@Service` annotation;
- Inject `GameRepository` using `@Autowired`;
- Implement methods to retrieve `all Games` (`findAll`);
- Use `@Transactional`(readOnly = true) to ensure the method runs within a read-only transaction.
- Implement methods to retrieve `Game by IDs` (`findById`);
- Implement methods to retrieve `Games by Game List` (`findByGameList`).
- Implement the logic to retrieve games by list ID, and map the result to a list of `GameMinDTO` objects.
#### 4.2 Game Controller Class Requirements for GameDTO and GameMinDTO:
- Use `@RestController` annotation.
- Map requests (`@RequestMapping`) to the `/games` endpoint;
- Inject `GameService` using `@Autowired`;
- Implement a method to handle `GET` requests and return all games (`@GetMapping`).
- Implement a method to handle `GET` requests and return games find by id (`@GetMapping(value = "/{id}`")
***
### 5. GameList Entity, Repository, Service and Resource Classes:
#### 5.1 Entity Class Requirements for GameList:
- Create the `GameList` Entity Class;
- Annotate the class with `@Entity` and `@Table`(name = "tb_game_list") to map it to the database table;
- Basic Attributes;
- Annotate `@Id` and `@GeneratedValue` for the primary key, and `@Column` for other fields;
- Constructors;
- Getters & Setters;
- hashCode & equals;
- Serializable.
#### 5.2 Repository Class Requirement for GameList:
- Create an interface that extends JpaRepository for the Game entity (`extends JpaRepository<GameList, Long>;`);
#### 5.3 GameList Service Class Requirements for GameDTO and GameMinDTO:
- Use `@Service` annotation;
- Inject `GameListRepository` using `@Autowired`;
- Implement Methods to Retrieve All Game `Lists` (`findAll`);
- Use `@Transactional`(readOnly = true) to ensure the method runs within a read-only transaction;
- Map the result to a list of `GameListDTO objects;
- Implement Method to Retrieve a `GameList` by ID (`findById`);
- Find the `GameList` by ID, and map it to a `GameListDTO` object.
- Implement methods to retrieve all categories (`findByGameList`)
- Implement the logic to retrieve games by game list, and map the result to a list of GameDTO or GameMinDTO objects.
#### 5.4 GameList Controller Class Requirements for GameDTO and GameMinDTO:
- Use `@RestController` annotation.
- Map requests (`@RequestMapping`) to the `/games` endpoint;
- Inject `GameService` using `@Autowired`;
- Implement a method to handle `GET` requests and return all games (`@GetMapping`).
- Implement a method to handle `GET` requests and return games find by id (`@GetMapping(value = "/{id}`")
***
### 6. Interface Requirements for GameMinProjection:
#### 6.1 Declare the Interface GameMinProjection:
- Define the interface with the relevant methods to retrieve only the necessary fields.
- Include Methods to Get Each Field: provide methods to get id, title, gameYear, imgUrl, shortDescription, and position.
#### 6.2 Repository Class Requirement for Game:
- Define Custom Queries with `@Query` Annotation;
- In this case, a custom native query is defined to retrieve games by list ID, ordered by their position in the list:
```java
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(nativeQuery = true, value = """
			SELECT tb_game.id, tb_game.title, tb_game.game_year AS gameYear, tb_game.img_url AS imgUrl,
			tb_game.short_description AS shortDescription, tb_belonging.position
			FROM tb_game
			INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
			WHERE tb_belonging.list_id = :listId
			ORDER BY tb_belonging.position
				""")
    List<GameMinProjection> searchByList(Long listId);
}
```
***
### 7. Database Seeding with Games, Belonging and GameList tables in the import.sql File and Persist Objects:
```sql
INSERT INTO tb_game_list (name) VALUES ('Adventure and RPG');
INSERT INTO tb_game_list (name) VALUES ('Platform Gaming');

INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Mass Effect Trilogy', 4.8, 2012, 'Role-playing (RPG), Shooter', 'XBox, Playstation, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/1.png', 'Embark on an intergalactic adventure filled with choice, consequence, and epic battles.', 'The Mass Effect Trilogy offers a deep narrative-driven RPG experience where every decision you make shapes the fate of the galaxy. Build relationships, engage in strategic combat, and uncover a rich universe teeming with mysteries and conflicts.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Red Dead Redemption 2', 4.7, 2018, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/2.png', 'A gripping tale of loyalty and survival in the dying days of the Wild West.', 'Explore a sprawling, immersive world in Red Dead Redemption 2. Experience the life of an outlaw, forge bonds, and navigate moral dilemmas as Arthur Morgan, a member of the notorious Van der Linde gang.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('The Witcher 3: Wild Hunt', 4.7, 2014, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/3.png', 'A legendary journey of a monster hunter in a world rife with danger and intrigue.', 'Step into the shoes of Geralt of Rivia, a Witcher, as you hunt monsters, unravel political conspiracies, and make impactful choices in a richly detailed open world.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Sekiro: Shadows Die Twice', 3.8, 2019, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/4.png', 'A challenging samurai epic that tests your skills and determination.', 'In Sekiro: Shadows Die Twice, you assume the role of a lone shinobi seeking revenge. Master precise combat mechanics and traverse a visually stunning yet perilous world.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Ghost of Tsushima', 4.6, 2012, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/5.png', 'A tale of honor and sacrifice set in feudal Japan.', 'Experience the journey of Jin Sakai, a samurai turned ghost, as he fights to reclaim his homeland from Mongol invaders. Ghost of Tsushima blends breathtaking visuals with an engaging narrative.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Super Mario World', 4.7, 1990, 'Platform', 'Super Ness, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/6.png', 'A timeless classic that redefined platform gaming.', 'Join Mario and Luigi on an epic adventure through Dinosaur Land to rescue Princess Peach from Bowser. Super Mario World features creative level designs and unforgettable gameplay.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Hollow Knight', 4.6, 2017, 'Platform', 'XBox, Playstation, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/7.png', 'A hauntingly beautiful journey into a mysterious underground world.', 'Hollow Knight offers a challenging yet rewarding metroidvania experience. Discover secrets, face tough enemies, and explore the expansive, interconnected world of Hallownest.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Ori and the Blind Forest', 4, 2015, 'Platform', 'XBox, Playstation, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/8.png', 'An emotional tale of bravery and friendship in a vibrant forest.', 'Ori and the Blind Forest combines a heartfelt story with visually stunning environments and fluid platforming mechanics. Embark on a journey to restore the forest’s balance.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Cuphead', 4.6, 2017, 'Platform', 'XBox, Playstation, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/9.png', 'A visually striking game inspired by 1930s cartoons.', 'Cuphead challenges players with intense boss battles and creative run-and-gun gameplay, all set against a backdrop of charming, hand-drawn visuals and jazzy music.');
INSERT INTO tb_game (title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES ('Sonic CD', 4, 1993, 'Platform', 'Sega CD, PC', 'https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/10.png', 'A fast-paced adventure through time with Sonic the Hedgehog.', 'In Sonic CD, race through vibrant levels and travel between past, present, and future to thwart Dr. Robotnik’s plans. This classic platformer delivers timeless speed and excitement.');

INSERT INTO tb_belonging (list_id, game_id, position) VALUES (1, 1, 0);
INSERT INTO tb_belonging (list_id, game_id, position) VALUES (1, 2, 1);
INSERT INTO tb_belonging (list_id, game_id, position) VALUES (1, 3, 2);
INSERT INTO tb_belonging (list_id, game_id, position) VALUES (1, 4, 3);
INSERT INTO tb_belonging (list_id, game_id, position) VALUES (1, 5, 4);

INSERT INTO tb_belonging (list_id, game_id, position) VALUES (2, 6, 0);
INSERT INTO tb_belonging (list_id, game_id, position) VALUES (2, 7, 1);
INSERT INTO tb_belonging (list_id, game_id, position) VALUES (2, 8, 2);
INSERT INTO tb_belonging (list_id, game_id, position) VALUES (2, 9, 3);
INSERT INTO tb_belonging (list_id, game_id, position) VALUES (2, 10, 4);
```
***
### 8. Retrieving GameMinDTO and GameDTO Data via Spring Boot RESTful API:
#### 8.1 Game:
```json
GET Request http://localhost:8080/games
```
```json
[
  {
    "id": 1,
    "title": "Mass Effect Trilogy",
    "year": 2012,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/1.png",
    "shortDescription": "Embark on an intergalactic adventure filled with choice, consequence, and epic battles."
  },
  {
    "id": 2,
    "title": "Red Dead Redemption 2",
    "year": 2018,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/2.png",
    "shortDescription": "A gripping tale of loyalty and survival in the dying days of the Wild West."
  },
  {
    "id": 3,
    "title": "The Witcher 3: Wild Hunt",
    "year": 2014,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/3.png",
    "shortDescription": "A legendary journey of a monster hunter in a world rife with danger and intrigue."
  },
  {
    "id": 4,
    "title": "Sekiro: Shadows Die Twice",
    "year": 2019,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/4.png",
    "shortDescription": "A challenging samurai epic that tests your skills and determination."
  },
  {
    "id": 5,
    "title": "Ghost of Tsushima",
    "year": 2012,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/5.png",
    "shortDescription": "A tale of honor and sacrifice set in feudal Japan."
  },
  {
    "id": 6,
    "title": "Super Mario World",
    "year": 1990,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/6.png",
    "shortDescription": "A timeless classic that redefined platform gaming."
  },
  {
    "id": 7,
    "title": "Hollow Knight",
    "year": 2017,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/7.png",
    "shortDescription": "A hauntingly beautiful journey into a mysterious underground world."
  },
  {
    "id": 8,
    "title": "Ori and the Blind Forest",
    "year": 2015,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/8.png",
    "shortDescription": "An emotional tale of bravery and friendship in a vibrant forest."
  },
  {
    "id": 9,
    "title": "Cuphead",
    "year": 2017,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/9.png",
    "shortDescription": "A visually striking game inspired by 1930s cartoons."
  },
  {
    "id": 10,
    "title": "Sonic CD",
    "year": 1993,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/10.png",
    "shortDescription": "A fast-paced adventure through time with Sonic the Hedgehog."
  }
]
```
#### 8.2 Game by Id:
```json
GET Request http://localhost:8080/games/6
```
```json
{
  "id": 6,
  "title": "Super Mario World",
  "year": 1990,
  "genre": "Platform",
  "platforms": "Super Ness, PC",
  "score": 4.7,
  "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/6.png",
  "shortDescription": "A timeless classic that redefined platform gaming.",
  "longDescription": "Join Mario and Luigi on an epic adventure through Dinosaur Land to rescue Princess Peach from Bowser. Super Mario World features creative level designs and unforgettable gameplay."
}
```
#### 8.3 GameList:
```json
GET Request http://localhost:8080/lists
```
```json
[
  {
    "id": 1,
    "name": "Adventure and RPG"
  },
  {
    "id": 2,
    "name": "Platform Gaming"
  }
]
```
#### 8.4 Game by List:
```json
GET Request http://localhost:8080/lists/2/games
```
```json
[
  {
    "id": 6,
    "title": "Super Mario World",
    "year": 1990,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/6.png",
    "shortDescription": "A timeless classic that redefined platform gaming."
  },
  {
    "id": 7,
    "title": "Hollow Knight",
    "year": 2017,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/7.png",
    "shortDescription": "A hauntingly beautiful journey into a mysterious underground world."
  },
  {
    "id": 8,
    "title": "Ori and the Blind Forest",
    "year": 2015,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/8.png",
    "shortDescription": "An emotional tale of bravery and friendship in a vibrant forest."
  },
  {
    "id": 9,
    "title": "Cuphead",
    "year": 2017,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/9.png",
    "shortDescription": "A visually striking game inspired by 1930s cartoons."
  },
  {
    "id": 10,
    "title": "Sonic CD",
    "year": 1993,
    "imgUrl": "https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/src/main/resources/static/img/10.png",
    "shortDescription": "A fast-paced adventure through time with Sonic the Hedgehog."
  }
]
```
***
### Steps Checklist:
:ballot_box_with_check: Implement Domain Model;<br/>
:ballot_box_with_check: Update Database Seed;<br/>
:ballot_box_with_check: Create GameDTO and Find Game by Id;<br/>
:ballot_box_with_check: Find All Lists in /lists;<br/>
:ballot_box_with_check: Create SQL Query, Projection, Find Games by List;<br/>
:ballot_box_with_check: Implement GameMinDTO Class;<br/>
:ballot_box_with_check: Implement Services and Repositories: GameService, GameRepository and GameListRepository;<br/>
:ballot_box_with_check: Define @Query Annotations in Repositories for Custom Queries;<br/>
:ballot_box_with_check: Create RestController for Game and GameList;<br/>
:ballot_box_with_check: Implement GET Methods to Retrieve Data via RESTful API.