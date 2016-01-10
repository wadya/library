package com.test.service

import com.test.model.Publisher
import com.test.repository.PublisherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityExistsException
import javax.persistence.NoResultException

/**
 * Created by Vadym Polishchuk on 1/5/16.
 * wadyasha@gmail.com
 */
@Service
class PublisherServiceBean implements PublisherService {

	@Autowired
	private PublisherRepository commonRepository

	@Override
	Collection<Publisher> findAll() {
		Collection<Publisher> commons = commonRepository.findAll()
		return commons
	}

	@Override
	Publisher findOne(Long id) {
		Publisher common = commonRepository.findOne(id)
		return common
	}

	@Override
	Publisher findByName(String name) {
		Publisher entity = commonRepository.findByName(name)
		return entity
	}

	@Override
	Publisher create(Publisher common) {
		if (common.getId() != null) {
			throw new EntityExistsException()
		}
		Publisher savedPublisher = commonRepository.save(common)
		return savedPublisher
	}

	@Override
	Publisher update(Publisher common) {
		Publisher persistedPublisher = findOne(common.getId())
		if (persistedPublisher == null) {
			throw new NoResultException()
		}
		Publisher updatedPublisher = commonRepository.save(common)
		return updatedPublisher
	}

	@Override
	void delete(Long id) {
		commonRepository.delete(id)
	}

}
