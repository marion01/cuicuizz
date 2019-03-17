CREATE TABLE Theme (
    Id	 INTEGER      PRIMARY KEY auto_increment,
    Name VARCHAR2(20) UNIQUE NOT NULL
);

CREATE TABLE Question (
    Id       INTEGER      PRIMARY KEY auto_increment,
    Question VARCHAR2(20) NOT NULL,
    ThemeId  INTEGER      NOT NULL
);

CREATE TABLE Answer (
    Id         INTEGER      PRIMARY KEY,
    QuestionId INTEGER      NOT NULL,
    Answer     VARCHAR2(20) NOT NULL,
    IsCorrect  INTEGER		NOT NULL
);

CREATE TABLE Mode (
    Id	 INTEGER	  PRIMARY KEY,
    Name VARCHAR2(20) NOT NULL
);
