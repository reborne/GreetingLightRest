package com.reborne.light.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reborne.light.model.Greeting;

@RestController
public class GreetingController {

	private static BigInteger nextId;
	private static Map<BigInteger,Greeting> greetingMap;
	
	private static Greeting save(Greeting greeting) {
	        if (greetingMap == null) {
	            greetingMap = new HashMap<BigInteger, Greeting>();
	            nextId = BigInteger.ONE;
	        }
	        /***
	         * Check object value, if that exists - update,
	         * if not - create
	         */
	        
	        if (greeting.getId() != null) {
	            Greeting oldGreeting = greetingMap.get(greeting.getId());
	            if (oldGreeting == null) {
	                return null;
	            }
	            greetingMap.remove(greeting.getId());
	            greetingMap.put(greeting.getId(), greeting);
	            return greeting;
	        }
	        
	        greeting.setId(nextId);
	        nextId = nextId.add(BigInteger.ONE);
	        greetingMap.put(greeting.getId(), greeting);
	        return greeting;
	}
	
	private static boolean delete(BigInteger id ) {
		Greeting greeting = greetingMap.remove(id);
		if (greeting == null) {
			return false;
		}
		return true;
	}
	
	static {
		Greeting g1 =  new Greeting();
		g1.setName("Hello");
		save(g1);
		
		Greeting g2 = new Greeting();
		g2.setName("Gracias");
		save(g2);
	}
	
	@RequestMapping(
			value = "/api/greetings",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings(){
		Collection<Greeting> greetings = greetingMap.values();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/greetings/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreetingById(@PathVariable("id") BigInteger id){
		Greeting greeting = greetingMap.get(id);
		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}
	
	@RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> createGreeting(
            @RequestBody Greeting greeting) {
        Greeting savedGreeting = save(greeting);
        return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
}

	@RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> updateGreeting(
            @RequestBody Greeting greeting) {

        Greeting updatedGreeting = save(greeting);
        if (updatedGreeting == null) {
            return new ResponseEntity<Greeting>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
}

	@RequestMapping(
			value = "/api/greetings/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(
			@PathVariable("id") BigInteger id, 
			@RequestBody Greeting greeting) {
		boolean isDeleted = delete(id);
		if (!isDeleted) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}
}
