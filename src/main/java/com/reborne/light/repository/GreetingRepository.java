package com.reborne.light.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reborne.light.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}
