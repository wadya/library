package com.test.service

import com.test.model.Author
import com.test.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityExistsException
import javax.persistence.NoResultException

/**
 * Created by Vadym Polishchuk on 1/5/16.
 * wadyasha@gmail.com
 */
@Service
class AuthorServiceBean implements AuthorService {

	@Autowired
	private AuthorRepository commonRepository

	@Override
	Collection<Author> findAll() {
		Collection<Author> commons = commonRepository.findAll()
		return commons
	}

	@Override
	Author findOne(Long id) {
		Author common = commonRepository.findOne(id)
		return common
	}
	@Override
	Author findByName(String name) {
		Author entity = commonRepository.findByName(name)
		return entity
	}

	@Override
	Author create(Author common) {
		if (common.getId() != null) {
			throw new EntityExistsException()
		}
		Author savedAuthor = commonRepository.save(common)
		return savedAuthor
	}

	@Override
	Author update(Author common) {
		Author persistedAuthor = findOne(common.getId())
		if (persistedAuthor == null) {
			throw new NoResultException()
		}
		Author updatedAuthor = commonRepository.save(common)
		return updatedAuthor
	}

	@Override
	void delete(Long id) {
		commonRepository.delete(id)
	}

}
