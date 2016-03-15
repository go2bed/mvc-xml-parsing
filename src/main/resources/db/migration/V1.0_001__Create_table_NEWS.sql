CREATE TABLE NEWS
(
  ID INTEGER  PRIMARY KEY AUTO_INCREMENT NOT NULL,
  TITLE VARCHAR(100) NOT NULL,
  NEWS_DATE DATE NOT NULL,
  BRIEF VARCHAR(500) NOT NULL,
  CONTENT VARCHAR(2048) NOT NULL
);
