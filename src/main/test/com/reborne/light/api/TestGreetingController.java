package com.reborne.light.api;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.reborne.light.AbstractControllerTest;
import com.reborne.light.service.GreetingService;

@Transactional
public class TestGreetingController extends AbstractControllerTest {
		
	@Autowired
	private GreetingService greetingService;
	
	@Before
	public void setUp() {
		super.setUp();
		greetingService.evictCache();
	}
	
	@Test
	public void testGetGreetings() throws Exception {
		String url = "/api/greetings";
		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders
						.get(url)
							.accept(MediaType.APPLICATION_JSON))
								.andReturn();
		
		String content = result.getResponse().getContentAsString();
		int statusCode = result.getResponse().getStatus();
		Assert.assertEquals("Test Failed - expected HTTP status 200",
						200, 
						statusCode);
		Assert.assertTrue("Test Failed - expected HTTP Response body to have a value",
				content.trim().length() > 0);
	}
}
