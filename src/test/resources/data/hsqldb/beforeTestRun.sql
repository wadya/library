TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK;

INSERT INTO Author (name) VALUES ('Artur');
INSERT INTO Author (name) VALUES ('Herbert');

INSERT INTO Publisher (name) VALUES ('Orelly');
INSERT INTO Publisher (name) VALUES ('Manning');

INSERT INTO Book (original_id, title, publisher_id) VALUES ('QCbUxHcYLskC', 'Java 7', 0);
INSERT INTO Book (original_id, title, publisher_id) VALUES ('QCbUxHcYLskB', 'Java 8', 1);
