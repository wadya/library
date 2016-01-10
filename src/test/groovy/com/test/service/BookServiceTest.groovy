package com.test.service

import com.test.model.Book
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Vadym Polishchuk on 1/9/16.
 * wadyasha@gmail.com
 */
class BookServiceTest extends CommonServiceTest<Book, BookService> {

	@Before
	public void setUp() {
		genericEntity = new Book()
		setter = "setTitle"
		getter = "getTitle"
	}

	@Override
	@Test
	public void testCreate() {
		genericEntity."$setter"("Test")
		genericEntity.setOriginalId("QCbUxHcYLskH")
		Book createdEntity = service.create(genericEntity)
		Assert.assertNotNull("failure - expect not null", createdEntity)
		Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getId())
		Assert.assertEquals("failure - expected name attribute match", "Test", genericEntity."$getter"())
		Collection<Book> list = service.findAll()
		Assert.assertEquals("failure - expected size", 3, list.size())
	}

	@Test
	public void testFindByOriginalId() {
		String originalId = "QCbUxHcYLskC"
		Book entity = service.findByOriginalId(originalId)
		Assert.assertNotNull("failure - expect not null", entity)
		Assert.assertEquals("failure - expected originalId attribute match", originalId, entity.getOriginalId())
	}
}
