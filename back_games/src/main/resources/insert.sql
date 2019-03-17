INSERT INTO Theme (id, name) VALUES (1, 'theme1');
INSERT INTO Theme (id, name) VALUES (2, 'theme2');

INSERT INTO Question (id, question, themeId) VALUES (1, 'question1', 1); 
INSERT INTO Question (id, question, themeId) VALUES (2, 'question2', 1);
INSERT INTO Question (id, question, themeId) VALUES (3, 'question3', 1); 
INSERT INTO Question (id, question, themeId) VALUES (4, 'question4', 1); 

INSERT INTO Answer (id, questionId, answer, isCorrect) VALUES (1, 1, 'answer1', 0);
INSERT INTO Answer (id, questionId, answer, isCorrect) VALUES (2, 1, 'answer2', 1);
INSERT INTO Answer (id, questionId, answer, isCorrect) VALUES (3, 2, 'answer3', 1);
INSERT INTO Answer (id, questionId, answer, isCorrect) VALUES (4, 2, 'answer4', 0);
INSERT INTO Answer (id, questionId, answer, isCorrect) VALUES (5, 3, 'answer5', 0);
INSERT INTO Answer (id, questionId, answer, isCorrect) VALUES (6, 3, 'answer6', 1);
INSERT INTO Answer (id, questionId, answer, isCorrect) VALUES (7, 4, 'answer7', 1);
INSERT INTO Answer (id, questionId, answer, isCorrect) VALUES (8, 4, 'answer8', 0);

INSERT INTO Mode (id, name) VALUES (1, 'mode1');
INSERT INTO Mode (id, name) VALUES (2, 'mode2');
INSERT INTO Mode (id, name) VALUES (3, 'mode3');