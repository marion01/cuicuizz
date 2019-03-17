CREATE TABLE User (
    id             INTEGER		PRIMARY KEY auto_increment,
    pseudo         VARCHAR2(20) UNIQUE NOT NULL,
    password       VARCHAR2(64) NOT NULL,
    lastActionDate VARCHAR2(20)
);

CREATE TABLE Score (
    id          INTEGER		 PRIMARY KEY auto_increment,
    userId      INTEGER		 NOT NULL,
    mode        VARCHAR2(20) NOT NULL,
    theme       VARCHAR2(20) NOT NULL,
    nbQuestions INTEGER		 NOT NULL,
    nbSuccess   INTEGER		 NOT NULL,
    value       VARCHAR2(20) NOT NULL
);