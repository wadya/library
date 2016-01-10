Task
---
Please write a java command line program to import books from json file into database.

Program should get as a parameter json file with books and import it into database.

It should print simple statistics after importing, e.g.: 

* how many books where imported, 
* how many books is already in database
* list of the books that where skipped, because they where already in database,

Configuration for database connection should be read from conf.json file.

Please fell free to use any java language(java, groovy,...), library and database you want.

It would be nice if you could use maven(or gradle) to build it, but it's not necessary. 
You will find files with books to import in books_0.json and books_1.json.

Those fields from json file should be imported into DB.

* id
* title
* authors
* publisher
* publishedDate
* pageCount

Howto
---

**Run application:**<br> 
 gradle bootRun -Pargs="books_0.json books_1.json books_0.json"

**Run tests:**<br> 
 gradle test
 
Notes
---
 
For solving this task I used:

* Language - [Groovy](http://www.groovy-lang.org/) 
* Framework - [SpringBoot](http://projects.spring.io/spring-boot/)
* Build automation system - [Gradle](http://gradle.org/)
* Database - [HSQLDB](http://hsqldb.org/) 
* For parsing JSON file - [GSON](https://github.com/google/gson)
* To store and retrieve data in a relational database - [Spring Data JPA](https://spring.io/guides/gs/accessing-data-jpa/)

Details
---

I made 3 data models:

* Author
* Publisher
* Book

Book model contains Many to One relation with Publisher model and Many to Many relation with Author model.

Because of using SpringBoot as framework I used application.properties file for configuration application.