package com.reborne.light.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reborne.light.model.Greeting;
import com.reborne.light.repository.GreetingRepository;

@Service
public class GreetingServiceBean implements GreetingService {
	
	@Autowired
	private GreetingRepository greetingRepository;

	public Collection<Greeting> findAll() {
		Collection<Greeting> greetings = greetingRepository.findAll();
        return greetings;
	}

	public Greeting findOne(Long id) {
		Greeting greeting = greetingRepository.findOne(id);
        return greeting;
	}

	public Greeting create(Greeting greeting) {
		if (greeting.getId() != null) {
            return null;
        }
        Greeting savedGreeting = greetingRepository.save(greeting);
        return savedGreeting;
	}

	public Greeting update(Greeting greeting) {
		 Greeting greetingToUpdate = findOne(greeting.getId());
	        if (greetingToUpdate == null) {
	            return null;
	        }
	        greetingToUpdate.setName(greeting.getName());
	        Greeting updatedGreeting = greetingRepository.save(greetingToUpdate);
	        return updatedGreeting;
	}

	public void delete(Long id) {
		greetingRepository.delete(id);
	}
}
