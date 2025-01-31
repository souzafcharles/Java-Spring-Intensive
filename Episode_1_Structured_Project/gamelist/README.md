# Episode 1: Structured Project
## Project Requirements and Configurations:
### Game Domain Model Entities:
![Game Domain Model Entities](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_1_Structured_Project/gamelist/game-model.png)
***
#### 1. Requirements Specification:
- Spring Initializr:
    - Maven;
    - Java 21;
    - Packing JAR;
    - Dependencies: Spring Web, Spring Data JPA, H2 Database and PostgresSQL Driver.
#### 1.2 Plug-in Maven:
```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-resources-plugin</artifactId>
	<version>3.1.0</version> <!--$NO-MVN-MAN-VER$ -->
</plugin>
```
#### 2. Setting up the application.properties File:
```properties
spring.profiles.active=${APP_PROFILE:test}
spring.jpa.open-in-view=false

cors.origins=${CORS_ORIGINS:http://localhost:5173,http://localhost:3000}
```
#### 3. Create and Setting up the application-test.properties File:
```properties
# H2 Connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 Client
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Show SQL
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
#### 4. Game and GameMinDTO Entities, Repository, Service and Controller Classes:
#### 4.1 Entity Class Requirements for Game:
- Create the `Game` Entity Class;
- Annotate the class with `@Entity` and `@Table`(name = "tb_game") to map it to the database table;
- Basic Attributes;
- Annotate `@Id` and `@GeneratedValue` for the primary key, and `@Column` for other fields;
- Constructors;
- Getters & Setters;
- hashCode & equals;
- Serializable.
#### 4.2 Entity Class Requirements for GameMinDTO:
- Create the `GameMinDTO` Class;
- Basic Attributes (id, title, year, imgUrl and shortDescription);
- Implement a no-argument constructor;
- Implement a constructor that accepts a `Game` entity object to initialize its attributes;
- Getters & Setters;
- Serializable.
#### 4.3 Repository Class Requirement for Game:
- Extends JpaRepository<Game, Long>.
#### 4.4 Game Service Class Requirements for GameMinDTO:
- Use `@Service` annotation;
- Inject `GameRepository` using `@Autowired`;
- Implement methods to retrieve all categories (`findAll`);
#### 4.5 Game Controller Class Requirements for GameMinDTO:
- Use `@RestController` annotation.
- Map requests (`@RequestMapping`) to the `/games` endpoint;
- Inject `GameService` using `@Autowired`;
- Implement a method to handle `GET` requests and return all categories (`@GetMapping`).
#### 5. Database Seeding with Games in the import.sql File and Persist Objects:
```sql
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
```
#### 6. Retrieving GameMinDTO Data via Spring Boot RESTful API:
GET Request /games:
```JSON
Game
GET http://localhost:8080/games 
```
````json
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
````
***
### 7. Steps Checklist:
:ballot_box_with_check: Create Maven project / library;<br/>
:ballot_box_with_check: Properties files, Game Entity, ORM - Game Seeding;<br/>
:ballot_box_with_check: Create GameMinDTO, GameRepository, GameService and GameController Classes; <br/>
:ballot_box_with_check: Test Retrieving GameMinDTO Data via Spring Boot RESTful API; <br/>
:ballot_box_with_check: Save on Github.<br/>
