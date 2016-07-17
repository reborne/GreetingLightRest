package com.reborne.light.model;

import java.math.BigInteger;

public class Greeting {

	private BigInteger id;
	
	private String name;
	
	public Greeting() {
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Greeting [id=" + id + ", name=" + name + "]";
	}
	
}
