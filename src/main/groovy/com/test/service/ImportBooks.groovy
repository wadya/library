package com.test.service
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import com.test.model.Author
import com.test.model.Book
import com.test.model.Publisher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

import java.text.ParseException
/**
 * Created by Vadym Polishchuk on 1/10/16.
 * wadyasha@gmail.com
 */
@Service
class ImportBooks {

	@Autowired
	private AuthorService authorService

	@Autowired
	private Environment environment

	@Autowired
	private PublisherService publisherService

	@Autowired
	private BookService bookService

	private Logger logger = LoggerFactory.getLogger(this.getClass())

	@Autowired
	private Environment env

	void importBooks(String filename) {
		Collection<Book> skippedBooks = new ArrayList<>()
		Collection<Book> importedBooks = new ArrayList<>()
		Integer initialBookSize = bookService.findAll().size()
		String defaultImportDirectory = env.getProperty("app.defaultImportDirectory");
		Map<String, Object> items
		try {
			items = new Gson().fromJson(new FileReader("${defaultImportDirectory}${filename}"),
					new TypeToken<HashMap<String, Object>>() {}.getType())
		} catch (FileNotFoundException e) {
			logger.error("File ${filename} not found in directory ${defaultImportDirectory}, import - impossible!")
			return
		} catch (JsonParseException jpe) {
			logger.error("File ${filename} can't be parsed, structure of JSON maybe broken, import - impossible!")
		}
		if (!items) {
			logger.error("Section 'items' not found in ${filename}! JSON file maybe broken, import - impossible!")
			return
		}
		items.get("items").each { item ->
			Set<Author> authors = new HashSet<>()
			def bookInfo = item?.volumeInfo
			if (!bookInfo) {
				logger.error("can't find volumeInfo section in json structure, import - impossible!")
				return
			} else {
				bookInfo?.authors?.each { authorName ->
					Author author = authorService.findByName(authorName)
					if (!author) {
						Author newAuthor = new Author()
						newAuthor.setName(authorName)
						author = authorService.create(newAuthor)
					}
					authors.add(author)
				}

				String publisherName = bookInfo?.publisher
				Publisher publisher = publisherService.findByName(publisherName)
				if (!publisher) {
					Publisher newPublisher = new Publisher()
					newPublisher.setName(publisherName)
					publisher = publisherService.create(newPublisher)
				}

				Book book = bookService.findByOriginalId(item?.id)
				if (!book) {
					Book newBook = new Book()
					newBook.setOriginalId(item?.id)
					newBook.setTitle(bookInfo?.title)
					newBook.setPageCount(bookInfo?.pageCount?.toInteger())
					Date publishedDate
					try {
						publishedDate = Date.parse("yyyy-MM-dd", bookInfo?.publishedDate)
					} catch (ParseException e) {
						try {
							publishedDate = Date.parse("MM-yyyy", bookInfo?.publishedDate)
						} catch (ParseException ee) {
							try {
								publishedDate = Date.parse("yyyy", bookInfo?.publishedDate)
							} catch (ParseException eee) {
								logger.warn("unknown date format in 'publishedDate' field, publishedDate not parsed!")
							}
						}
					}
					if (publishedDate) {
						newBook.setPublishedDate(publishedDate)
					}
					newBook.setPublisher(publisher)
					book = bookService.create(newBook)
					book.setAuthors(authors)
					bookService.update(book)
					importedBooks.add(book)
				} else {
					skippedBooks.add(book)
				}
			}
		}

		logger.info("==============================================================")
		logger.info("Begin of Import report - import data from file ${filename}")
		logger.info("==============================================================")
		logger.info("initial books count - ${initialBookSize}")
		logger.info("skipped books count - ${skippedBooks.size()}")
		if (skippedBooks.size() > 0) {
			logger.info("skipped books list:")
			skippedBooks.each { logger.info("	${it.getTitle()}") }
		}
		logger.info("imported books count - ${importedBooks.size()}")
		logger.info("total books count - ${bookService.findAll().size()}")
		logger.info("==============================================================")
		logger.info("End of Import report - import data from file ${filename}")
		logger.info("==============================================================")
	}
}
