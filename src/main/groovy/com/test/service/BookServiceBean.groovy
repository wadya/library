package com.test.service

import com.test.model.Book
import com.test.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityExistsException
import javax.persistence.NoResultException

/**
 * Created by Vadym Polishchuk on 1/5/16.
 * wadyasha@gmail.com
 */
@Service
class BookServiceBean implements BookService {

	@Autowired
	private BookRepository commonRepository

	@Override
	Collection<Book> findAll() {
		Collection<Book> commons = commonRepository.findAll()
		return commons
	}

	@Override
	Book findByOriginalId(String originalId) {
		Book entity = commonRepository.findByOriginalId(originalId)
		return entity
	}

	@Override
	Book findOne(Long id) {
		Book common = commonRepository.findOne(id)
		return common
	}

	@Override
	Book create(Book common) {
		if (common.getId() != null) {
			throw new EntityExistsException()
		}
		Book savedBook = commonRepository.save(common)
		return savedBook
	}

	@Override
	Book update(Book common) {
		Book persistedBook = findOne(common?.getId())
		if (persistedBook == null) {
			throw new NoResultException()
		}
		Book updatedBook = commonRepository.save(common)
		return updatedBook
	}

	@Override
	void delete(Long id) {
		commonRepository.delete(id)
	}

}
