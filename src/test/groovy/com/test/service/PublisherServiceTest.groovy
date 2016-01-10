package com.test.service

import com.test.model.Publisher
import org.junit.Assert
import org.junit.Before
import org.junit.Test
/**
 * Created by Vadym Polishchuk on 1/9/16.
 * wadyasha@gmail.com
 */
class PublisherServiceTest extends CommonServiceTest<Publisher, PublisherService> {

	@Before
	public void setUp() {
		genericEntity = new Publisher()
	}

	@Test
	public void testFindByName() {
		String name = "Orelly"
		Publisher entity = service.findByName(name)
		Assert.assertNotNull("failure - expect not null", entity)
		Assert.assertEquals("failure - expected originalId attribute match", name, entity.getName())
	}
}
