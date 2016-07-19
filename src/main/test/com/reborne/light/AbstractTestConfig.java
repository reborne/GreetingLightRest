package com.reborne.light;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reborne.light.GreetingLightRestApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=GreetingLightRestApp.class)
public abstract class AbstractTestConfig {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
}
