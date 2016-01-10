package com.test.service

import com.test.model.Author

/**
 * Created by Vadym Polishchuk on 1/5/16.
 * wadyasha@gmail.com
 */
interface AuthorService {

	Collection<Author> findAll()

	Author findOne(Long id)

	Author findByName(String name)

	Author create(Author obj)

	Author update(Author obj)

	void delete(Long id)
}