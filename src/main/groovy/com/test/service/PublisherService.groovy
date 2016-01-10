package com.test.service
import com.test.model.Publisher
/**
 * Created by Vadym Polishchuk on 1/5/16.
 * wadyasha@gmail.com
 */
interface PublisherService {

	Collection<Publisher> findAll()

	Publisher findOne(Long id)

	Publisher findByName(String name)

	Publisher create(Publisher obj)

	Publisher update(Publisher obj)

	void delete(Long id)
}