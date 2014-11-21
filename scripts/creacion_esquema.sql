-- password is 'somePassword' hashed con http://www.nitrxgen.net/hashgen/
CREATE USER 'pepe'@'localhost' IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194'; 

DROP SCHEMA IF EXISTS `booksmov_db`;
CREATE SCHEMA IF NOT EXISTS `booksmov_db`;

USE `booksmov_db`;

DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `Preferences`;
DROP TABLE IF EXISTS `Authors`;
DROP TABLE IF EXISTS `Actors`;
DROP TABLE IF EXISTS `Books`;
DROP TABLE IF EXISTS `Movies`;
DROP TABLE IF EXISTS `Directors`;
DROP TABLE IF EXISTS `Products`;
DROP TABLE IF EXISTS `loans`;

CREATE  TABLE `Preferences` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `language` VARCHAR(10) NULL,
  `country` VARCHAR(10) NULL,
  PRIMARY KEY (`id`)  );

CREATE  TABLE `Users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `preference_id` INT NULL,
  PRIMARY KEY (`id_user`)  );
  
ALTER TABLE `Users` ADD CONSTRAINT fk_preferences FOREIGN KEY (`preference_id`) REFERENCES `Preferences`(`id`);

CREATE  TABLE `Actors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(100) NOT NULL,
  `movie_id` INT NOT NULL,
  PRIMARY KEY (`id`)  );

CREATE  TABLE `Authors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(100) NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`id`)  );
  
CREATE  TABLE `Directors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)  );

CREATE  TABLE `Products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `rating` VARCHAR(10) NOT NULL,
  `already_used` BOOLEAN,
  `borrowable` BOOLEAN,
  `user_id` INT NOT NULL,
  `image` LONGBLOB,
  PRIMARY KEY (`id`)  );
  
CREATE  TABLE `Books` (
  `id` INT NOT NULL,
  `description` TEXT,
  PRIMARY KEY (`id`)  );

CREATE  TABLE `Movies` (
  `id` INT NOT NULL,
  `format` VARCHAR(20) NOT NULL,
  `director_id` INT NULL,
  PRIMARY KEY (`id`)  );
  
 CREATE  TABLE `loans` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `request_desc` VARCHAR(500) NOT NULL,
  `state` VARCHAR(20) NOT NULL,
  `requester_id` INT NOT NULL,
  `consignee_id` INT NOT NULL,
  `request_date` DATETIME NOT NULL,
  `response_date` DATETIME NULL,
  `delivery_date` DATETIME NULL,
  PRIMARY KEY (`id`)  );
  
  
  ALTER TABLE `loans` ADD CONSTRAINT fk_loan_product FOREIGN KEY (`product_id`)
  REFERENCES `Products`(`id`);
  
  ALTER TABLE `loans` ADD CONSTRAINT fk_loan_requester FOREIGN KEY (`requester_id`)
  REFERENCES `Users`(`id_user`);
  
  ALTER TABLE `loans` ADD CONSTRAINT fk_loan_consignee FOREIGN KEY (`consignee_id`)
  REFERENCES `Users`(`id_user`);
	
  ALTER TABLE `Authors` ADD CONSTRAINT fk_author_book FOREIGN KEY (`book_id`)
  REFERENCES `Books`(`id`);
  
  ALTER TABLE `Actors` ADD CONSTRAINT fk_actor_movie FOREIGN KEY (`movie_id`)
  REFERENCES `Movies`(`id`);
  
  ALTER TABLE `Movies` ADD CONSTRAINT fk_director FOREIGN KEY (`director_id`)
  REFERENCES `Directors`(`id`);
   
  ALTER TABLE `Products` ADD CONSTRAINT fk_product_user FOREIGN KEY (`user_id`)
  REFERENCES `Users`(`id_user`);

  ALTER TABLE `Movies` ADD CONSTRAINT fk_product_movie FOREIGN KEY (`id`)
  REFERENCES `Products`(`id`);

  ALTER TABLE `Books` ADD CONSTRAINT fk_product_book FOREIGN KEY (`id`)
  REFERENCES `Products`(`id`);
  
INSERT INTO `Preferences` (language, country) VALUES ('es', 'AR');
INSERT INTO `Preferences` (language, country) VALUES ('es', 'ES');
INSERT INTO `Preferences` (language, country) VALUES ('en', 'US');
INSERT INTO `Preferences` (language, country) VALUES ('en', 'UK');

INSERT INTO `Users` (name, password, preference_id) VALUES ('Andres', '123456', 1);
INSERT INTO `Users` (name, password, preference_id) VALUES ('Diego', '123456', 2);
INSERT INTO `Users` (name, password, preference_id) VALUES ('John', '123456', 3);
INSERT INTO `Users` (name, password, preference_id) VALUES ('Paul', '123456', 4);
INSERT INTO `Users` (name, password, preference_id) VALUES ('David', '123456', 1);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Berenice', 4, '1', '1', 1);
INSERT INTO `books` (id, description) VALUES (1, 'Berenice is a short horror story by Edgar Allan Poe, first published in the Southern Literary Messenger in 1835. The story follows a man named Egaeus who is preparing to marry his cousin Berenice. He has a tendency to fall into periods of intense focus during which he seems to separate himself from the outside world. Berenice begins to deteriorate from an unnamed disease until the only part of her remaining healthy is her teeth, which Egaeus begins to obsess over. Berenice is buried, and Egaeus continues to contemplate her teeth. One day Egaeus wakes up from a period of focus with an uneasy feeling, and the sound of screams in his ears. A servant startles him by telling him Berenices grave has been disturbed, and she is still alive; but beside Egaeus is a box containing 32 blood-stained teeth and a poem about "visiting the grave of my beloved."');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (1,'Edgar Allan Poe',1);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Harry Potter and the Deathly Hallows', 5, '0', '1', 3);
INSERT INTO `books` (id, description) VALUES (2, 'Harry is waiting in Privet Drive. The Order of the Phoenix is coming to escort him safely away without Voldemort and his supporters knowing - if they can. But what will Harry do then? How can he fulfill the momentous and seemingly impossible task that Professor Dumbledore has left him? The epic finale to an epic series.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (2,'J.K. Rowling',2), (3, 'Mary GrandPré', 2);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Time Travelers Wife', 3, '1', '0', 1);
INSERT INTO `books` (id, description) VALUES (3, 'Audrey Niffeneggers dazzling debut is the story of Clare, a beautiful, strong-minded art student, and Henry, an adventuresome librarian, who have known each other since Clare was six and Henry was thirty-six, and were married when Clare was twenty-three and Henry thirty-one. Impossible but true, because Henry is one of the first people diagnosed with Chrono-Displacement Disorder: his genetic clock randomly resets and he finds himself misplaced in time, pulled to moments of emotional gravity from his life, past and future. His disappearances are spontaneous and unpredictable, and lend a spectacular urgency to Clare and Henrys unconventional love story. That their attempt to live normal lives together is threatened by something they can neither prevent nor control makes their story intensely moving and entirely unforgettable.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (4,'Audrey Niffenegger', 3);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Water for Elephants', 2, '0', '1', 5);
INSERT INTO `books` (id, description) VALUES (4, 'Orphaned, penniless, Jacob Jankowski jumps a freight train in the dark, and in that instant, transforms his future. By morning, he has landed a job with the Flying Squadron of the Benzini Brothers Most Spectacular Show on Earth. By nightfall, he is in love. In an America made colourless by prohibition and the Depression, the circus is a refuge of sequins and sensuality. But behind the glamour lies a darker world, where both animals and men are dispensable. Where falling in love is the most dangerous act of all...');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (5,'Sara Gruen', 4);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Atonement', 4, '1', '1', 4);
INSERT INTO `books` (id, description) VALUES (5, 'Ian McEwan’s symphonic novel of love and war, childhood and class, guilt and forgiveness provides all the satisfaction of a brilliant narrative and the provocation we have come to expect from this master of English prose.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (6,'Ian McEwan', 5);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Da Vinci Code', 5, '0', '1', 1);
INSERT INTO `books` (id, description) VALUES (6, 'An ingenious code hidden in the works of Leonardo da Vinci. A desperate race through the cathedrals and castles of Europe. An astonishing truth concealed for centuries . . . unveiled at last. While in Paris, Harvard symbologist Robert Langdon is awakened by a phone call in the dead of the night. The elderly curator of the Louvre has been murdered inside the museum, his body covered in baffling symbols. As Langdon and gifted French cryptologist Sophie Neveu sort through the bizarre riddles, they are stunned to discover a trail of clues hidden in the works of Leonardo da Vinci—clues visible for all to see and yet ingeniously disguised by the painter.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (7,'Dan Brown', 6);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Harry Potter and the Deathly Hallows', 5, '1', '0', 4);
INSERT INTO `books` (id, description) VALUES (7, 'Harry is waiting in Privet Drive. The Order of the Phoenix is coming to escort him safely away without Voldemort and his supporters knowing - if they can. But what will Harry do then? How can he fulfill the momentous and seemingly impossible task that Professor Dumbledore has left him? The epic finale to an epic series.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (8,'J.K. Rowling',7), (9, 'Mary GrandPré', 7);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Shadow of the Wind', 4, '1', '1', 2);
INSERT INTO `books` (id, description) VALUES (8, 'Barcelona, 1945: A city slowly heals in the aftermath of the Spanish Civil War, and Daniel, an antiquarian book dealers son who mourns the loss of his mother, finds solace in a mysterious book entitled The Shadow of the Wind, by one Julián Carax. But when he sets out to find the authors other works, he makes a shocking discovery: someone has been systematically destroying every copy of every book Carax has written. In fact, Daniel may have the last of Caraxs books in existence. Soon Daniels seemingly innocent quest opens a door into one of Barcelonas darkest secrets--an epic story of murder, madness, and doomed love.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (10,'Carlos Ruiz Zafón',8), (11, 'Lucia Graves', 8);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('American Gods', 3, '1', '0', 3);
INSERT INTO `books` (id, description) VALUES (9, 'Days before his release from prison, Shadows wife, Laura, dies in a mysterious car crash. Numbly, he makes his way back home. On the plane, he encounters the enigmatic Mr Wednesday, who claims to be a refugee from a distant war, a former god and the king of America. Together they embark on a profoundly strange journey across the heart of the USA, whilst all around them a storm of preternatural and epic proportions threatens to break.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (12,'Veronica Roth', 9);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Divergent', 2, '0', '0', 5);
INSERT INTO `books` (id, description) VALUES (10, 'In Beatrice Priors dystopian Chicago world, society is divided into five factions, each dedicated to the cultivation of a particular virtue--Candor (the honest), Abnegation (the selfless), Dauntless (the brave), Amity (the peaceful), and Erudite (the intelligent). On an appointed day of every year, all sixteen-year-olds must select the faction to which they will devote the rest of their lives. For Beatrice, the decision is between staying with her family and being who she really is--she cant have both. So she makes a choice that surprises everyone, including herself.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (13,'Neil Gaiman', 10);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Kafka on the Shore', 5, '1', '1', 1);
INSERT INTO `books` (id, description) VALUES (11, 'Kafka on the Shore, a tour de force of metaphysical reality, is powered by two remarkable characters: a teenage boy, Kafka Tamura, who runs away from home either to escape a gruesome oedipal prophecy or to search for his long-missing mother and sister; and an aging simpleton called Nakata, who never recovered from a wartime affliction and now is drawn toward Kafka for reasons that, like the most basic activities of daily life, he cannot fathom. Their odyssey, as mysterious to them as it is to us, is enriched throughout by vivid accomplices and mesmerizing events. Cats and people carry on conversations, a ghostlike pimp employs a Hegel-quoting prostitute, a forest harbors soldiers apparently unaged since World War II, and rainstorms of fish (and worse) fall from the sky. There is a brutal murder, with the identity of both victim and perpetrator a riddle - yet this, along with everything else, is eventually answered, just as the entwined destinies of Kafka and Nakata are gradually revealed, with one escaping his fate entirely and the other given a fresh start on his own.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (14,'Haruki Murakami',11), (15, 'Philip Gabriel', 11);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Graveyard Book', 3, '0', '0', 3);
INSERT INTO `books` (id, description) VALUES (12, 'After the grisly murder of his entire family, a toddler wanders into a graveyard where the ghosts and other supernatural residents agree to raise him as one of their own. Nobody Owens, known to his friends as Bod, is a normal boy. He would be completely normal if he did not live in a sprawling graveyard, being raised and educated by ghosts, with a solitary guardian who belongs to neither the world of the living nor of the dead. There are dangers and adventures in the graveyard for a boy. But if Bod leaves the graveyard, then he will come under attack from the man Jack—who has already killed Bods family . . . Beloved master storyteller Neil Gaiman returns with a luminous new novel for the audience that embraced his New York Times bestselling modern classic Coraline. Magical, terrifying, and filled with breathtaking adventures, The Graveyard Book is sure to enthrall readers of all ages.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (16,'Neil Gaiman',12), (17, 'Dave McKean', 12);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Other Boleyn Girl', 4, '1', '1', 2);
INSERT INTO `books` (id, description) VALUES (13, 'When Mary Boleyn comes to court as an innocent girl of fourteen, she catches the eye of Henry VIII. Dazzled, Mary falls in love with both her golden prince and her growing role as unofficial queen. However, she soon realises just how much she is a pawn in her familys ambitious plots as the kings interest begins to wane and she is forced to step aside for her best friend and rival: her sister, Anne. Then Mary knows that she must defy her family and her king and take fate into her own hands. A rich and compelling novel of love, sex, ambition, and intrigue, The Other Boleyn Girl introduces a woman of extraordinary determination and desire who lived at the heart of the most exciting and glamourous court in Europe and survived by following her heart.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (18,'Philippa Gregory', 13);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Snow Flower and the Secret Fan', 2, '0', '0', 5);
INSERT INTO `books` (id, description) VALUES (14, 'In nineteenth-century China, in a remote Hunan county, a girl named Lily, at the tender age of seven, is paired with a laotong, “old same,” in an emotional match that will last a lifetime. The laotong, Snow Flower, introduces herself by sending Lily a silk fan on which she’s painted a poem in nu shu, a unique language that Chinese women created in order to communicate in secret, away from the influence of men. As the years pass, Lily and Snow Flower send messages on fans, compose stories on handkerchiefs, reaching out of isolation to share their hopes, dreams, and accomplishments. Together, they endure the agony of foot-binding, and reflect upon their arranged marriages, shared loneliness, and the joys and tragedies of motherhood. The two find solace, developing a bond that keeps their spirits alive. But when a misunderstanding arises, their deep friendship suddenly threatens to tear apart.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (19,'Lisa See', 14);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Edgar Allan Poe Novels', 5, '1', '1', 1);
INSERT INTO `books` (id, description) VALUES (15, 'Edgar Allan Poe stories collection "The Raven, The Gold-Bug, The Black Cat, The Masque of the Red Death, The Pit and the Pendulum, The Shadow, Some Words with a Mummy, The Angel of the Odd, The Sphinx, A Tale of the Ragged Mountains, The Purloined Letter, The Man of the Crowd, Mystification". Poe and his works influenced literature in the United States and around the world, as well as in specialized fields, such as cosmology and cryptography. Poe and his work appear throughout popular culture in literature, music, films, and television.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (20,'Edgar Allan Poe',15), (21, 'Alexey Daranov', 15), (22, 'Pavel Alexeev', 15);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Thirteenth Tale', 5, '1', '1', 2);
INSERT INTO `books` (id, description) VALUES (16, 'iographer Margaret Lea returns one night to her apartment above her father’s antiquarian bookshop. On her steps she finds a letter. It is a hand-written request from one of Britain’s most prolific and well-loved novelists. Vida Winter, gravely ill, wants to recount her life story before it is too late, and she wants Margaret to be the one to capture her history. The request takes Margaret by surprise — she doesn’t know the author, nor has she read any of Miss Winter’s dozens of novels.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (23,'Edgar Allan Poe',16);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Harry Potter and the Deathly Hallows', 5, '1', '1', 4);
INSERT INTO `books` (id, description) VALUES (17, 'Harry is waiting in Privet Drive. The Order of the Phoenix is coming to escort him safely away without Voldemort and his supporters knowing - if they can. But what will Harry do then? How can he fulfill the momentous and seemingly impossible task that Professor Dumbledore has left him? The epic finale to an epic series.');
INSERT INTO `authors` (`id`, `full_name`, `book_id`) VALUES (24,'J.K. Rowling',7), (25, 'Mary GrandPré', 17);

/*----------------------Usuario 1 movies-------------------*/

INSERT INTO `directors` (full_name) VALUES ('Chuck Russel');
INSERT INTO `directors` (full_name) VALUES ('Robert Zemeckis');
INSERT INTO `directors` (full_name) VALUES ('Peter Jackson');
INSERT INTO `directors` (full_name) VALUES ('George Lucas');
INSERT INTO `directors` (full_name) VALUES ('Andy Wachowski');
INSERT INTO `directors` (full_name) VALUES ('Louis Leterrier');
INSERT INTO `directors` (full_name) VALUES ('Wolfgang Petersen');
INSERT INTO `directors` (full_name) VALUES ('Francis Ford Coppola');
INSERT INTO `directors` (full_name) VALUES ('Peter Farrelly');

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('A Nightmare on Elm Street 3: Dream Warriors', 4, '1', '1', 1);
INSERT INTO `movies` (id, format, director_id) VALUES (18, 'VHS', 1);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (1,'Robert Englund',18);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Back to the future', 5, '1', '1', 2);
INSERT INTO `movies` (id, format, director_id) VALUES (19, 'DVD', 2);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (2,'Michael Fox',19), (3, 'Christopher Lloyd', 19);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Lord of the Rings: the Fellowship of the Ring', 3, '0', '1', 3);
INSERT INTO `movies` (id, format, director_id) VALUES (20, 'BLURAY', 3);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (4,'Elijah Wood',20), (5, 'Ian McKellen', 20), (6, 'Viggo Mortensen', 20);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Lord of the Rings: the two towers', 5, '1', '1', 4);
INSERT INTO `movies` (id, format, director_id) VALUES (21, 'BLURAY', 3);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (7,'Elijah Wood',21), (8, 'Ian McKellen', 21), (9, 'Viggo Mortensen', 21);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Lord of the Rings: the return of the king', 4, '0', '1', 5);
INSERT INTO `movies` (id, format, director_id) VALUES (22, 'BLURAY', 3);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (10,'Elijah Wood',22), (11, 'Ian McKellen', 22), (12, 'Viggo Mortensen', 22);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Cast Away', 2, '1', '0', 1);
INSERT INTO `movies` (id, format, director_id) VALUES (23, 'VHS', 2);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (13,'Tom Hanks',23), (14, 'Helen Hunt', 23);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Star Wars', 1, '0', '0', 2);
INSERT INTO `movies` (id, format, director_id) VALUES (24, 'VHS', 4);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (15,'Harrison Ford',24), (16, 'Mark Hamill', 24);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Star Wars: Episodio VII', 3, '1', '0', 3);
INSERT INTO `movies` (id, format, director_id) VALUES (25, 'DVD', 4);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (17,'Harrison Ford',25), (18, 'Mark Hamill', 25);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Matrix', 5, '1', '1', 4);
INSERT INTO `movies` (id, format, director_id) VALUES (26, 'BLURAY', 5);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (19,'Keanu Reeves',26);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Transporter', 4, '0', '1', 5);
INSERT INTO `movies` (id, format, director_id) VALUES (27, 'DVD', 6);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (20,'Jason Statham',27);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Troy', 5, '1', '1', 1);
INSERT INTO `movies` (id, format, director_id) VALUES (28, 'DVD', 7);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (21,'Brad Pitt',28);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Beowulf', 5, '0', '1', 2);
INSERT INTO `movies` (id, format, director_id) VALUES (29, 'BLURAY', 2);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (22,'Ray Winstone',29), (23, 'Crispin Glover', 29), (24, 'Angelina Jolie', 29);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('The Godfather', 5, '1', '0', 3);
INSERT INTO `movies` (id, format, director_id) VALUES (30, 'VHS', 8);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (25,'Marlon Brando',30), (26, 'Al Pacino', 30);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Dumb & Dumber', 3, '1', '1', 4);
INSERT INTO `movies` (id, format, director_id) VALUES (31, 'VHS', 9);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (27,'Jim Carrey',31), (28, 'Jeff Daniels', 31);

INSERT INTO `Products` (title, rating, already_used, borrowable, user_id) VALUES ('Ace Ventura: When Nature Calls', 4, '0', '1', 5);
INSERT INTO `movies` (id, format, director_id) VALUES (32, 'DVD', 9);
INSERT INTO `actors` (`id`, `full_name`, `movie_id`) VALUES (29,'Jim Carrey',32);

GRANT ALL PRIVILEGES ON `booksmov_db`.* TO 'pepe'@'localhost'
  IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194';
