package com.reborne.light.services;

import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.reborne.light.AbstractTestConfig;
import com.reborne.light.model.Greeting;
import com.reborne.light.service.GreetingService;

@Transactional
public class GreetingsServiceTest extends AbstractTestConfig {
	
	@Autowired
	private GreetingService greetingService;
	
	@Before
	public void setup() {
	}
	
	@After
	public void wipeout() {
	}
	
	@Test
	public void searchAll() {
		Collection<Greeting> result = greetingService.findAll();
		Assert.assertNotNull("failure - expected not null", result);
		Assert.assertEquals("failure - expected list size", 2, result.size());
	}
	
	
	@Test
	public void searchOneEntry() {
		Long id = new Long(1);
		Greeting result = greetingService.findOne(id);
		Assert.assertNotNull("Test failed - expeted result", result);
		Assert.assertEquals("failure - expected id attribute match",
				id,
				result.getId());
	}
	
	 @Test
	public void testFindOneNotFound() {
		 Greeting entity = greetingService.findOne(Long.MAX_VALUE);
		 Assert.assertNull("failure - expected null", entity);
	}
	 
	 @Test
	 public void testCreateGreeting() {
		 Greeting greeting = new Greeting();
		 greeting.setName("test");
		 
		 Greeting createdGreeting = greetingService.create(greeting);
		 
		 Assert.assertNotNull("failure - expected not null", createdGreeting);
	     Assert.assertNotNull("failure - expected id attribute not null",
	    		 			 createdGreeting.getId());
	     
	     Assert.assertEquals("failure - expected text attribute match",
	    		 			 "test",
	    		 			 createdGreeting.getName());

	     Collection<Greeting> resultList = greetingService.findAll();

	     Assert.assertEquals("failure - expected size", 3, resultList.size());
	 }
	 
	 @Test @Ignore
	 //TODO: java.lang.AssertionError: Test failed - expected exception
	 public void createGreetingWithId() {
		 Exception exception = null;
		 
		 Greeting greeting = new Greeting();
		 greeting.setId(Long.MAX_VALUE);
		 greeting.setName("test");
		 
		 try {
			 greetingService.create(greeting);
		 } catch(Exception e) {
			 exception = e;
		 }
		 
		 Assert.assertNotNull("Test failed - expected exception: ", exception);
		 Assert.assertTrue("failure - expected EntityExistsException",
	        		exception instanceof EntityExistsException);
	 }
	 
	 @Test
	    public void testUpdateGreeting() {

	        Long id = new Long(1);

	        Greeting entity = greetingService.findOne(id);

	        Assert.assertNotNull("failure - expected not null", entity);

	        String updatedText = entity.getName() + " test";
	        entity.setName(updatedText);
	        Greeting updatedEntity = greetingService.update(entity);

	        Assert.assertNotNull("failure - expected not null", updatedEntity);
	        Assert.assertEquals("failure - expected id attribute match", id,
	                updatedEntity.getId());
	        Assert.assertEquals("failure - expected text attribute match",
	                updatedText, updatedEntity.getName());
	}
	 
	 @Test @Ignore
	//TODO: java.lang.AssertionError: failure - expected exception
	 public void testUpdateNotFound() {

	    Exception exception = null;

	    Greeting entity = new Greeting();
	    entity.setId(Long.MAX_VALUE);
	    entity.setName("test");

	    try {
	        greetingService.update(entity);
	    } catch (NoResultException e) {
	        exception = e;
	    }

	    Assert.assertNotNull("failure - expected exception", exception);
	    Assert.assertTrue("failure - expected NoResultException",
	            exception instanceof NoResultException);
	 }
	 
	 @Test
	 public void testDelete() {
	        Long id = new Long(1);
	        Greeting entity = greetingService.findOne(id);
	        Assert.assertNotNull("failure - expected not null", entity);
	        greetingService.delete(id);
	        Collection<Greeting> list = greetingService.findAll();
	        Assert.assertEquals("failure - expected size", 1, list.size());
	        Greeting deletedEntity = greetingService.findOne(id);
	        Assert.assertNull("failure - expected null", deletedEntity);
	}
}
