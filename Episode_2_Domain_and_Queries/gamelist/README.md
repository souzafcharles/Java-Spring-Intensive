# Episode 2: Domain and Queries
## Project Requirements and Configurations:
### 1. Relationships:
#### 1.1 Game Domain Model Entities:
![Order Domain Model Entities](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_2_Domain_and_Queries/gamelist/order-model.png)
#### 1.2 Object Relational Model:
![Object Relational Model](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_2_Domain_and_Queries/gamelist/object-relational.png)
#### 1.3 Table Relational Model:
![Table Relational Model](https://github.com/souzafcharles/Java-Spring-Intensive/blob/main/Episode_2_Domain_and_Queries/gamelist/table-relational.png)
***
## Steps to Follow and Documentation:
### 2. Customised SQL Query in the GameRepository Interface:
```java
@Query(nativeQuery = true, value = """
		SELECT tb_game.id, tb_game.title, tb_game.game_year AS `year`, tb_game.img_url AS imgUrl,
		tb_game.short_description AS shortDescription, tb_belonging.position
		FROM tb_game
		INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
		WHERE tb_belonging.list_id = :listId
		ORDER BY tb_belonging.position
		""")
List<GameMinProjection> searchByList(Long listId);
```
***
### 3. Database Seeding with Games in the import.sql File and Persist Objects:
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
### 4. Retrieving GameMinDTO Data via Spring Boot RESTful API:
```JSON
Game
GET http://localhost:8080/games  

Game by id
GET http://localhost:8080/games/1

Game list
http://localhost:8080/lists

Game by list
http://localhost:8080/lists/1/games
```
***
### Steps Checklist:
- Implement Domain Model;
- Update Database Seed;
- Create GameDTO and Find Game by Id;
- Find All Lists in /lists;
- Create SQL Query, Projection, Find Games by List.

