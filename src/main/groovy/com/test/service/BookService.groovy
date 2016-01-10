package com.test.service

import com.test.model.Book

/**
 * Created by Vadym Polishchuk on 1/5/16.
 * wadyasha@gmail.com
 */
interface BookService {

	Collection<Book> findAll()

	Book findByOriginalId(String originalId)

	Book findOne(Long id)

	Book create(Book obj)

	Book update(Book obj)

	void delete(Long id)
}