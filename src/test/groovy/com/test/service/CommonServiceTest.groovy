package com.test.service
import com.test.AbstractLibraryApplicationTests
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityExistsException
import javax.persistence.NoResultException
/**
 * Created by Vadym Polishchuk on 1/8/16.
 * wadyasha@gmail.com
 */
@Transactional
public abstract class CommonServiceTest<T,S> extends AbstractLibraryApplicationTests {

	@Autowired
	protected S service

	protected T genericEntity

	protected String setter = "setName"
	protected String getter = "getName"

	@Test
	public void testCreate() {
		genericEntity."$setter"("Test")
		T createdEntity = service.create(genericEntity)
		Assert.assertNotNull("failure - expect not null", createdEntity)
		Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getId())
		Assert.assertEquals("failure - expected name attribute match", "Test", genericEntity."$getter"())
		Collection<T> list = service.findAll()
		Assert.assertEquals("failure - expected size", 3, list.size())
	}

	@Test
	public void testCreateWithId() {
		Exception e = null
		genericEntity.setId(Long.MAX_VALUE)
		genericEntity."$setter"("Test")
		try {
			service.update(genericEntity)
		} catch (NoResultException nre) {
			e = nre
		}
		Assert.assertNotNull("failure - expected exception", e)
		Assert.assertTrue("failure - expected NoResultException", e instanceof NoResultException)
	}

	@Test
	public void testUpdateNotFound() {
		Exception e = null
		genericEntity.setId(Long.MAX_VALUE)
		genericEntity."$setter"("Test")
		try {
			service.create(genericEntity)
		} catch (EntityExistsException eee) {
			e = eee
		}
		Assert.assertNotNull("failure - expected exception", e)
		Assert.assertTrue("failure - expected EntityExistsException", e instanceof EntityExistsException)
	}

	@Test
	public void testFindAll() {
		Collection<T> list = service.findAll()
		Assert.assertNotNull("failure - expect not null", list)
		Assert.assertEquals("failure - expected size", 2, list.size())
	}

	@Test
	public void testFindOne() {
		Long id = new Long(0L)
		T entity = service.findOne(id)
		Assert.assertNotNull("failure - expect not null", entity)
		Assert.assertEquals("failure - expected id attribute match", id, entity.getId())
	}

	@Test
	public void testFindOneNotFound() {
		Long id = Long.MAX_VALUE
		T entity = service.findOne(id)
		Assert.assertNull("failure - expected null", entity)
	}


	@Test
	public void testUpdate() {
		Long id = new Long(0L)
		T entity = service.findOne(id)
		Assert.assertNotNull("failure - expect not null", entity)
		String updatedName = entity."$getter"() + " test"
		entity."$setter"(updatedName)
		T updatedEntity = service.update(entity)
		Assert.assertNotNull("failure - expect updated entity not null", updatedEntity)
		Assert.assertEquals("failure - expected id attribute match", id, updatedEntity.getId())
		Assert.assertEquals("failure - expected name attribute match", updatedName, updatedEntity."$getter"())
	}

	@Test
	public void testDelete(){
		Long id = new Long(0L)
		T entity = service.findOne(id)
		Assert.assertNotNull("failure - expect not null", entity)
		service.delete(id)
		Collection<T> list = service.findAll()
		Assert.assertEquals("failure - expected size", 1, list.size())
		T deletedEntity = service.findOne(id)
		Assert.assertNull("failure - expected entity to be deleted", deletedEntity)
	}
}
