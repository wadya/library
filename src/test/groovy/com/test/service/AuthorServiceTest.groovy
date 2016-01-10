package com.test.service
import com.test.model.Author
import org.junit.Assert
import org.junit.Before
import org.junit.Test
/**
 * Created by Vadym Polishchuk on 1/9/16.
 * wadyasha@gmail.com
 */
class AuthorServiceTest extends CommonServiceTest<Author, AuthorService> {

	@Before
	public void setUp() {
		genericEntity = new Author()
	}

	@Test
	public void testFindByName() {
		String name = "Artur"
		Author entity = service.findByName(name)
		Assert.assertNotNull("failure - expect not null", entity)
		Assert.assertEquals("failure - expected originalId attribute match", name, entity.getName())
	}
}
