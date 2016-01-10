DROP TABLE Book_Author IF EXISTS;
DROP TABLE Book IF EXISTS;
DROP TABLE Author IF EXISTS;
DROP TABLE Publisher IF EXISTS;

CREATE TABLE Author (
  id BIGINT IDENTITY PRIMARY KEY NOT NULL,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE Publisher (
  id BIGINT IDENTITY PRIMARY KEY NOT NULL,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE Book (
  id BIGINT IDENTITY PRIMARY KEY NOT NULL,
  original_id VARCHAR(25) NOT NULL,
  title VARCHAR(250) NOT NULL,
  publisher_id BIGINT,
  published_date DATETIME,
  page_count INT
);

CREATE TABLE Book_Author (
  book_id BIGINT NOT NULL,
  author_id BIGINT NOT NULL,
  PRIMARY KEY (book_id,author_id),
  CONSTRAINT FK_Book_Author_Book_id FOREIGN KEY (book_id) REFERENCES Book(id),
  CONSTRAINT FK_Book_Author_Author_id FOREIGN KEY (author_id) REFERENCES Author(id)
);